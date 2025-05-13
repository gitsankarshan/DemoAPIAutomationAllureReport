package requestPayloads;

public class UserPayload {

    private String name;
    private String job;

    // Constructor
    public UserPayload(String name, String job) {
        this.name = name;
        this.job = job;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for job
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    // Override toString() for better logging
    @Override
    public String toString() {
        return "UserPayload {name='" + name + "', job='" + job + "'}";
    }
}








//package requestPayloads;
//
//public class UserPayload {
//    private String name;
//    private String job;
//
//    public UserPayload(String name, String job) {
//        this.name = name;
//        this.job = job;
//    }
//
//    public String getName() { return name; }
//    public String getJob() { return job; }
//
//    public void setName(String name) { this.name = name; }
//    public void setJob(String job) { this.job = job; }
//}
