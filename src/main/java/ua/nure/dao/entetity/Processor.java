package ua.nure.dao.entetity;

public class Processor {
    private int id;
    private String model;
    private String cores;
    private String frequency;

    public Processor(int id, String model, String cores, String frequency) {
        this.id = id;
        this.model = model;
        this.cores = cores;
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Processor{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", cores='" + cores + '\'' +
                ", frequency='" + frequency + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCores() {
        return cores;
    }

    public void setCores(String cores) {
        this.cores = cores;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }


    public static class Builder{
        private int id;
        private String model;
        private String cores;
        private String frequency;

        public Builder setCores(String cores) {
            this.cores = cores;
            return this;
        }
        public Builder setId(int id) {
            this.id = id;
            return this;
        }
        public Builder setModel(String model) {
            this.model = model;
            return this;
        }
        public Builder setFrequency(String frequency) {
            this.frequency = frequency;
            return this;
        }

        public Processor build(){
            return new Processor(id, model, cores, frequency);
        }
    }
}
