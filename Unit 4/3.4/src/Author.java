class Author {
    protected String code;
    protected String name;
    protected String nationality;

    public Author(String code, String name, String nationality) {
        setCode(code);
        setName(name);
        setNationality(nationality);

    }

    public  Author(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Author{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }

    public void setCode(String code) {
        this.code = code;
    }
}