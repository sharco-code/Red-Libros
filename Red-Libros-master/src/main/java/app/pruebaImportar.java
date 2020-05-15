package app;

import service.ImportService;

public class pruebaImportar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 ImportService importService = new ImportService();
		 try {
			 //importService.ImportarCurso("C:\\Users\\Usuario\\Downloads\\cursos.xml");
			 //importService.ImportarContenido("C:\\Users\\Usuario\\Downloads\\contenidos.xml");
			 importService.ImportarGrupo("C:\\Users\\Usuario\\Downloads\\grups.xml");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 
	}

}
