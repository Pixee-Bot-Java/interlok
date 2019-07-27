package com.adaptris.core.services.conditional.conditions;

import com.adaptris.annotation.AdapterComponent;
import com.adaptris.annotation.ComponentProfile;
import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.CoreException;
import com.adaptris.core.services.conditional.Condition;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * This {@link Condition} allows you to negate a child {@link Condition}'s result.
 * </p>
 *
 * @config not
 * @author bklair
 *
 */
@XStreamAlias("not")
@AdapterComponent
@ComponentProfile(summary = "Allows you to negates the given condition result", tag = "condition")
public class ConditionNot extends ConditionListImpl {
  @NotNull
  @Valid
  private Condition condition;

  @Override
  public boolean evaluate(AdaptrisMessage message) throws CoreException {
    log.trace("Testing not condition");
    return !condition.evaluate(message);
  }

  public Condition getCondition() {
    return condition;
  }

  public void setCondition(Condition condition) {
    this.condition = condition;
  }
}
