
package com.gft.app.elw.model.workflow;

import java.io.Serializable;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "version",
    "processName",
    "startActivity",
    "workflow"
})
@Generated("jsonschema2pojo")
public class Workflowschema implements Serializable
{

    @JsonProperty("version")
    private String version;
    @JsonProperty("processName")
    private String processName;
    @JsonProperty("startActivity")
    private String startActivity;
    @JsonProperty("workflow")
    private Workflow workflow;
    private final static long serialVersionUID = -6400989299083367164L;

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    @JsonProperty("processName")
    public String getProcessName() {
        return processName;
    }

    @JsonProperty("processName")
    public void setProcessName(String processName) {
        this.processName = processName;
    }

    @JsonProperty("startActivity")
    public String getStartActivity() {
        return startActivity;
    }

    @JsonProperty("startActivity")
    public void setStartActivity(String startActivity) {
        this.startActivity = startActivity;
    }

    @JsonProperty("workflow")
    public Workflow getWorkflow() {
        return workflow;
    }

    @JsonProperty("workflow")
    public void setWorkflow(Workflow workflow) {
        this.workflow = workflow;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Workflowschema.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("version");
        sb.append('=');
        sb.append(((this.version == null)?"<null>":this.version));
        sb.append(',');
        sb.append("processName");
        sb.append('=');
        sb.append(((this.processName == null)?"<null>":this.processName));
        sb.append(',');
        sb.append("startActivity");
        sb.append('=');
        sb.append(((this.startActivity == null)?"<null>":this.startActivity));
        sb.append(',');
        sb.append("workflow");
        sb.append('=');
        sb.append(((this.workflow == null)?"<null>":this.workflow));
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
        result = ((result* 31)+((this.workflow == null)? 0 :this.workflow.hashCode()));
        result = ((result* 31)+((this.version == null)? 0 :this.version.hashCode()));
        result = ((result* 31)+((this.processName == null)? 0 :this.processName.hashCode()));
        result = ((result* 31)+((this.startActivity == null)? 0 :this.startActivity.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Workflowschema) == false) {
            return false;
        }
        Workflowschema rhs = ((Workflowschema) other);
        return (((((this.workflow == rhs.workflow)||((this.workflow!= null)&&this.workflow.equals(rhs.workflow)))&&((this.version == rhs.version)||((this.version!= null)&&this.version.equals(rhs.version))))&&((this.processName == rhs.processName)||((this.processName!= null)&&this.processName.equals(rhs.processName))))&&((this.startActivity == rhs.startActivity)||((this.startActivity!= null)&&this.startActivity.equals(rhs.startActivity))));
    }

}
