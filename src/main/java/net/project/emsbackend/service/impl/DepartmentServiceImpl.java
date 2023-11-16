package net.project.emsbackend.service.impl;

import lombok.AllArgsConstructor;
import net.project.emsbackend.dto.DepartmentDto;
import net.project.emsbackend.entity.Department;
import net.project.emsbackend.exception.ResourceNotFoundException;
import net.project.emsbackend.mapper.DepartmentMapper;
import net.project.emsbackend.repository.DepartmentRepository;
import net.project.emsbackend.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {

        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);

        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {

        Department department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException("Department does not exists with the given id : " +departmentId)
        );
        return DepartmentMapper.mapToDepartmentDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map((department) -> DepartmentMapper.mapToDepartmentDto(department))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedepartment) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException("Department does not exists with the given id : " +departmentId)
        );

        department.setDepartmentName(updatedepartment.getDepartmentName());
        department.setDepartmentDdescription(updatedepartment.getDepartmentDescription());

        Department savedDepartment = departmentRepository.save(department);

        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        departmentRepository.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException(("Department does not exists with this id: " +departmentId)));
        departmentRepository.deleteById(departmentId);
    }
}
