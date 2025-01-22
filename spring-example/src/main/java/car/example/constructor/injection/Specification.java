package car.example.constructor.injection;

public class Specification {
    private String make;
    private String model;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Specification{");
        sb.append("make=").append(make);
        sb.append(", model=").append(model);
        sb.append('}');
        return sb.toString();
    }
}
