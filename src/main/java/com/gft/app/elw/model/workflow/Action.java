
package com.gft.app.elw.model.workflow;

import java.io.Serializable;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "actionName",
    "actionClass"
})
@Generated("jsonschema2pojo")
public class Action implements Serializable
{

    @JsonProperty("actionName")
    private String actionName;
    @JsonProperty("actionClass")
    private String actionClass;
    private final static long serialVersionUID = -9210980346962127353L;

    @JsonProperty("actionName")
    public String getActionName() {
        return actionName;
    }

    @JsonProperty("actionName")
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    @JsonProperty("actionClass")
    public String getActionClass() {
        return actionClass;
    }

    @JsonProperty("actionClass")
    public void setActionClass(String actionClass) {
        this.actionClass = actionClass;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Action.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("actionName");
        sb.append('=');
        sb.append(((this.actionName == null)?"<null>":this.actionName));
        sb.append(',');
        sb.append("actionClass");
        sb.append('=');
        sb.append(((this.actionClass == null)?"<null>":this.actionClass));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.actionClass == null)? 0 :this.actionClass.hashCode()));
        result = ((result* 31)+((this.actionName == null)? 0 :this.actionName.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Action) == false) {
            return false;
        }
        Action rhs = ((Action) other);
        return (((this.actionClass == rhs.actionClass)||((this.actionClass!= null)&&this.actionClass.equals(rhs.actionClass)))&&((this.actionName == rhs.actionName)||((this.actionName!= null)&&this.actionName.equals(rhs.actionName))));
    }

}
