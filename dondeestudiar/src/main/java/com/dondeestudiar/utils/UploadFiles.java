package com.dondeestudiar.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;


public class UploadFiles {

//    protected final Log logger = LogFactory.getLog(this.getClass());
	
	public String codificarNombre(String filename) {
		String uniqueName = UUID.randomUUID().toString()+"_"+filename;
		return uniqueName;
	}

    public String subirImagenFTP( MultipartFile file, String ruta ) throws IOException{
        BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
        String uniqueID = codificarNombre(file.getOriginalFilename());

        FTPClient cliente = new FTPClient();
        cliente.connect(Constantes.FTP_HOST);
        cliente.login(Constantes.FTP_USER, Constantes.FTP_PASSWORD);
        cliente.changeWorkingDirectory(Constantes.FTP_DIRECTORY+ruta);
        cliente.setFileType(FTP.BINARY_FILE_TYPE);
        cliente.enterLocalPassiveMode();
        cliente.storeFile(uniqueID, bis);

        if(bis != null) bis.close();
        if(cliente != null){
            cliente.logout();
            cliente.disconnect();
        }

        return uniqueID;
    }

    public boolean eliminarImagenFTP(String ruta, String filename) throws IOException{
	    FTPClient cliente = new FTPClient();
	    boolean eliminado;
        cliente.connect(Constantes.FTP_HOST);
        cliente.login(Constantes.FTP_USER, Constantes.FTP_PASSWORD);
        eliminado = cliente.deleteFile(Constantes.FTP_DIRECTORY+ruta+filename);
        cliente.logout();
        cliente.disconnect();
        return eliminado;
    }

	public String subirFoto(MultipartFile file, String ruta) throws IOException {
		String filename = "";
		if(!file.isEmpty()) {
			filename = UUID.randomUUID().toString()+"_"+file.getOriginalFilename();
			Path rutaRaizRelativa = Paths.get(ruta).resolve(filename);
			Path rutaAbsoluta = rutaRaizRelativa.toAbsolutePath();
			Files.copy(file.getInputStream(), rutaAbsoluta);
		}
		return filename;
	}
	
	public Resource cargarImagen(String filename, String ruta) throws MalformedURLException {
		Path rutaFoto = getPath(filename, ruta);
		Resource recurso = new UrlResource(rutaFoto.toUri());
		if( !recurso.exists() || !recurso.isReadable() ) {
			throw new RuntimeException("Error: Ocurrio un error al cargar la imagen "+rutaFoto.toString());
		}
		return recurso;
	}
	
	public boolean eliminarImagen(String filename, String ruta) {
		Path rutaFoto = getPath(filename, ruta);
		File archivo = rutaFoto.toFile();
		if( archivo.exists() && archivo.canRead() ) {
			if( archivo.delete() )
				return true;
		}

		return false;
	}
	
	private Path getPath(String filename, String ruta) {
		return Paths.get(ruta).resolve(filename).toAbsolutePath();
	}
	
}
