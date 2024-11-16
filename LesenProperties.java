import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.sql.Date;
import java.util.Properties;  

//import org.apache.commons.io.FileUtils;

public class LesenProperties {

	public static void main(String[] args) throws IOException {
		LesenProperties properties = new LesenProperties();
		properties.getPropValues();
	}

	InputStream inputStream;

	String result = "";

	public String getPropValues() throws IOException {
		try {
			String tmpDirsLocation = System.getProperty("java.io.tmpdir");
			String propFileName = tmpDirsLocation + "gdt_rel.config.txt";
			Properties prop = new Properties();
			File fConfig = new File(propFileName);
			// FileUtils.forceMkdir(fConfig);
//Properties-Datei n.V. - mit Standardwerten neu anlegen:			
			if (fConfig.exists() == false) {
				try (OutputStream output = new FileOutputStream(propFileName)) {
					String userName = System.getProperty("user.name");
					prop.put("User", userName);
					prop.put("GDTExportPath", tmpDirsLocation);
					prop.put("GDTArchivePath", tmpDirsLocation);
					prop.put("DBFPath", tmpDirsLocation);
					prop.put("resmedTenant", "RMD");
					prop.put("ApiKey", "293619c3-7da9-4b3c-a781-7379d69ff164$$7b4e6443-54a7-47e0-9967-61f5611514b8"); //..Def: Testumgbg
					prop.put("ApiUrl", "https://uat-mdr.gdt.k8s.api.halloalberta.de/patient/"); //..Def: Testumgbg
					prop.put("primaryDoctorId", "5ab23b9f9d69c74b68cfbfb3");
					prop.put("regionId", "09ce9dc3-330b-4392-9f06-860ec49a9086");
					prop.put("classification", "nicht invasiv");
					prop.put("ErweiterteKonsoleMeldung", "false");
					prop.store(output, "Konfiguration GDT_REL01");
					System.out.println("Konfiguration mit Vorgabewerten neu angelegt: " + propFileName);
					System.out.println("!! Korrigieren Sie ggf. die Parameterdatei und starten das Programm neu !!");

				} catch (IOException e) {
					System.err.println("Fehler Konfiguration: " + e.getMessage());
				}
			}
//Properties einlesen:			
			inputStream = new FileInputStream(propFileName);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
//Parameter (global) füllen:			
			Parameter.User = prop.getProperty("User");
			Parameter.GDTExportPath = prop.getProperty("GDTExportPath");
			Parameter.GDTArchivePath = prop.getProperty("GDTArchivePath");
			Parameter.DBFPath = prop.getProperty("DBFPath");
			Parameter.resmedTenant = prop.getProperty("resmedTenant");
			Parameter.ApiKey = prop.getProperty("ApiKey");
			Parameter.ApiUrl = prop.getProperty("ApiUrl");
			Parameter.primaryDoctorId = prop.getProperty("primaryDoctorId");
			Parameter.regionId = prop.getProperty("regionId");
			Parameter.classification = prop.getProperty("classification");
			Parameter.ErweiterteKonsoleMeldung = prop.getProperty("ErweiterteKonsoleMeldung");
			//Date time = new Date(System.currentTimeMillis());
			result = "User = " + Parameter.User + ", " + 
					 "GDTExportPath = " + Parameter.GDTExportPath + ", " +
					 "GDTArchivePath = " + Parameter.GDTArchivePath + ", " +
					 "DBFPath = " + Parameter.DBFPath + ", " +
					 "resmedTenant = " + Parameter.resmedTenant + ", " +
					 "ApiKey = " + Parameter.ApiKey + ", " +
					 "ApiUrl = " + Parameter.ApiUrl + ", " +
					 "primaryDoctorId = " + Parameter.primaryDoctorId + ", " +
					 "regionId = " + Parameter.regionId + ", " +
					 "classification = " + Parameter.classification + ", " +
					 "ErweiterteKonsoleMeldung = " + Parameter.ErweiterteKonsoleMeldung;

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;
	}
}
