/*
 * Copyright 2015 Adaptris Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package com.adaptris.core.jms;

import static com.adaptris.core.jms.BasicPtpConsumerActiveErorHandlerTest.DEFAULT_XML_COMMENT;
import java.util.concurrent.TimeUnit;
import com.adaptris.core.ConfiguredProduceDestination;
import com.adaptris.core.StandaloneProducer;
import com.adaptris.core.jms.activemq.BasicActiveMqImplementation;
import com.adaptris.util.TimeInterval;

public class BasicPtpProducerActiveErrorHandlerTest extends JmsProducerCase {
  static final String DEFAULT_FILE_SUFFIX = "-JNDI";
  @Override
  public boolean isAnnotatedForJunit4() {
    return true;
  }

  @Override
  protected Object retrieveObjectForSampleConfig() {
    JmsConnection p = new JmsConnection(new BasicActiveMqImplementation("tcp://localhost:61616"));
    ActiveJmsConnectionErrorHandler erHandler = new ActiveJmsConnectionErrorHandler();
    erHandler.setCheckInterval(new TimeInterval(30L, TimeUnit.SECONDS));
    p.setConnectionErrorHandler(erHandler);
    return new StandaloneProducer(p, new PtpProducer(new ConfiguredProduceDestination("TheQueueToProduceTo")));
  }

  @Override
  protected String createBaseFileName(Object object) {
    return super.createBaseFileName(object) + "-" + ActiveJmsConnectionErrorHandler.class.getSimpleName();
  }

  @Override
  protected String getExampleCommentHeader(Object obj) {
    return super.getExampleCommentHeader(obj) + DEFAULT_XML_COMMENT;
  }
}
