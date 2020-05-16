package app;

import service.ImportService;

public class pruebaImportar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 ImportService importService = new ImportService();
		 try {
			 importService.importarCurso("C:\\Users\\Usuario\\Downloads\\cursos.xml");
			 importService.importarContenido("C:\\Users\\Usuario\\Downloads\\contenidos.xml");
			 importService.importarGrupo("C:\\Users\\Usuario\\Downloads\\grups.xml");
			 importService.importarAlumno("C:\\Users\\Usuario\\Downloads\\alumnesJoseRaul.xml");
			 importService.importarMatricula("C:\\Users\\Usuario\\Downloads\\matriculaJoseRaul.xml");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 
	}

}
