package com.example.doan.domain.employee;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "t_employee")
public class EmployeeEntity {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "contact_name")
    private String name;

    @ColumnInfo(name = "contact_email")
    private String email;

    public EmployeeEntity(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public EmployeeEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmployeeDto toDto() {
        EmployeeDto dto = new EmployeeDto();

        dto.setId(this.id);
        dto.setName(this.name);
        dto.setEmail(this.email);

        return dto;
    }
}