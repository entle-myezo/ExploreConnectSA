package za.ac.cput.repository;

import za.ac.cput.domain.Admin;
import za.ac.cput.util.Helper;

import java.util.*;
import java.util.stream.Collectors;

public class AdminRepository implements IAdminRepository {
    private static AdminRepository repo = null;
    private Map<Long, Admin> adminMap;

    private AdminRepository() {
        adminMap = new HashMap<>();
    }

    public static AdminRepository getRepository() {
        if (repo == null) {
            repo = new AdminRepository();
        }
        return repo;
    }

    @Override
    public Admin create(Admin admin) {
        if (admin.getUserId() == null) {
            throw new IllegalArgumentException("Admin ID cannot be null");
        }
        if (adminMap.containsKey(admin.getUserId())) {
            throw new IllegalArgumentException("Admin with ID " + admin.getUserId() + " already exists");
        }
        adminMap.put(admin.getUserId(), admin);
        return admin;
    }

    @Override
    public Admin read(Long id) {
        return adminMap.get(id);
    }

    @Override
    public Admin update(Admin admin) {
        if (admin.getUserId() == null) {
            throw new IllegalArgumentException("Admin ID cannot be null");
        }
        if (!adminMap.containsKey(admin.getUserId())) {
            throw new IllegalArgumentException("Admin with ID " + admin.getUserId() + " does not exist");
        }
        adminMap.put(admin.getUserId(), admin);
        return admin;
    }

    @Override
    public Admin delete(Long id) {
        return adminMap.remove(id);
    }

    @Override
    public List<Admin> getAll() {
        return new ArrayList<>(adminMap.values());
    }

    @Override
    public Admin findById(Long id) {
        return read(id);
    }

    @Override
    public Admin findByEmail(String email) {
        return adminMap.values().stream()
                .filter(admin -> admin.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Admin findByEmpId(String empId) {
        Helper.requireNotEmptyOrNull(empId, "Employee ID");
        return adminMap.values().stream()
                .filter(admin -> admin.getEmpId().equals(empId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Admin> findByDepartment(String department) {
        return adminMap.values().stream()
                .filter(admin -> admin.getDepartment() != null &&
                        admin.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }
}
