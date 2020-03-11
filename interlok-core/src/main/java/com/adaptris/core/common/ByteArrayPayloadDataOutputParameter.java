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

package com.adaptris.core.common;

import com.adaptris.annotation.DisplayOrder;
import com.adaptris.core.util.ExceptionHelper;
import com.adaptris.interlok.InterlokException;
import com.adaptris.interlok.config.DataOutputParameter;
import com.adaptris.interlok.types.InterlokMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.IOException;
import java.io.OutputStream;

/**
 * This {@code DataOutputParameter} is used when you want to write data to the {@link com.adaptris.core.AdaptrisMessage} payload.
 * <p>
 * An example might be specifying that the XML content required for the {@link com.adaptris.core.services.path.XPathService} can be
 * found in the payload of an {@link com.adaptris.core.AdaptrisMessage}.
 * </p>
 * 
 * @author andersonam
 * @config byte-array-payload-data-output-parameter
 * 
 */
@XStreamAlias("byte-array-payload-data-output-parameter")
@DisplayOrder(order = {"contentEncoding"})
public class ByteArrayPayloadDataOutputParameter implements DataOutputParameter<byte[]> {

  @Override
  public void insert(byte[] data, InterlokMessage msg) throws InterlokException {
    try (OutputStream os = msg.getOutputStream()) {
      os.write(data);
    } catch (Exception e) {
      throw ExceptionHelper.wrapCoreException(e);
    }
  }
}
