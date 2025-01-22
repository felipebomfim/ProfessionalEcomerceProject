package car.example.bean;

public class MyBean {
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void showMesssage(){
        System.out.println("Message: "+message);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MyBean{");
        sb.append("message=").append(message);
        sb.append('}');
        return sb.toString();
    }

}
