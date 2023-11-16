package net.project.emsbackend.mapper;

import net.project.emsbackend.dto.DepartmentDto;
import net.project.emsbackend.entity.Department;

public class DepartmentMapper {
    //convert department jpa entity into department dto
    public static DepartmentDto mapToDepartmentDto(Department department){
        return new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDdescription()
        );
    }

    //convert department dto into department jpa entity
    public static Department mapToDepartment(DepartmentDto departmentDto){
        return new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription()
        );
    }
}
