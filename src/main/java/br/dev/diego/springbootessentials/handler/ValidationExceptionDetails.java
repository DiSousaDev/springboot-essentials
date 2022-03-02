package br.dev.diego.springbootessentials.handler;

public class ValidationExceptionDetails extends StandardError {

    private String fieldName;
    private String fieldMessage;

    public ValidationExceptionDetails() {
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldMessage() {
        return fieldMessage;
    }

    public void setFieldMessage(String fieldMessage) {
        this.fieldMessage = fieldMessage;
    }
}
