package com.beerhouse.error;

public class ValidationErrorDetails extends ErrorDetails {
    private String fieldTypeName;
    private String fieldDefaultMessage;

    public static final class Builder {
        private String title;
        private int status;
        private String details;
        private Long timestamp;
        private String developerMessage;
        private String fieldTypeName;
        private String fieldDefaultMessage;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder details(String details) {
            this.details = details;
            return this;
        }

        public Builder timestamp(Long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public Builder fieldTypeName(String fieldTypeName) {
            this.fieldTypeName = fieldTypeName;
            return this;
        }

        public Builder fieldDefaultMessage(String fieldDefaultMessage) {
            this.fieldDefaultMessage = fieldDefaultMessage;
            return this;
        }

        public ValidationErrorDetails build() {
            ValidationErrorDetails validationErrorDetails = new ValidationErrorDetails();
            validationErrorDetails.status = this.status;
            validationErrorDetails.developerMessage = this.developerMessage;
            validationErrorDetails.details = this.details;
            validationErrorDetails.title = this.title;
            validationErrorDetails.timestamp = this.timestamp;
            validationErrorDetails.fieldTypeName = this.fieldTypeName;
            validationErrorDetails.fieldDefaultMessage = this.fieldDefaultMessage;

            return validationErrorDetails;
        }
    }

    public String getFieldTypeName() {
        return fieldTypeName;
    }

    public String getFieldMessage() {
        return fieldDefaultMessage;
    }
}
