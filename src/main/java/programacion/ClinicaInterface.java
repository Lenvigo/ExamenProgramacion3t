public interface ClinicaInterface {
    void insertVet();
    void insertMasc();
    void deleteVet(String dni);
    void deleteMasc(int id);
    void showPetData();
    void showVetFromMasc();

    // Se debe devolver la ruta al fichero exportado
    String exportVetHighSalary();
    void modifyVetSalary();
    void modifyVetMasc();
}

