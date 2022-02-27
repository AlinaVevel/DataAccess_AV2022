module com.alinavevel.libraryapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires jdk.unsupported;
    requires org.jboss.logging;
    requires android.json;


    opens com.alinavevel.libraryapp to javafx.fxml;
    exports com.alinavevel.libraryapp;
}