package com.beerhouse.error;

public class ErrorDetails {

    protected String title;
    protected int status;
    protected String details;
    protected Long timestamp;
    protected String developerMessage;


    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return status;
    }

    public String getDetails() {
        return details;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public static final class ErrorDetailsBuilder {
        protected String title;
        protected int status;
        protected String details;
        protected Long timestamp;
        protected String developerMessage;

        private ErrorDetailsBuilder() {
        }

        public static ErrorDetailsBuilder newBuilder() {
            return new ErrorDetailsBuilder();
        }

        public ErrorDetailsBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ErrorDetailsBuilder status(int status) {
            this.status = status;
            return this;
        }

        public ErrorDetailsBuilder details(String details) {
            this.details = details;
            return this;
        }

        public ErrorDetailsBuilder timestamp(Long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ErrorDetailsBuilder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public ErrorDetails build() {
            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.status = this.status;
            errorDetails.timestamp = this.timestamp;
            errorDetails.details = this.details;
            errorDetails.title = this.title;
            errorDetails.developerMessage = this.developerMessage;
            return errorDetails;
        }
    }
}
