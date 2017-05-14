package de.secdevops;

import org.owasp.appsensor.core.AppSensorClient;
import org.owasp.appsensor.core.Attack;
import org.owasp.appsensor.core.DetectionPoint;
import org.owasp.appsensor.core.DetectionSystem;
import org.owasp.appsensor.core.Event;
import org.owasp.appsensor.core.DetectionPoint.Category;
import org.owasp.appsensor.core.event.EventManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.secdevops.user.UserUtils;

@Service
public class AppsensorUtils {

	@Autowired 
	private AppSensorClient appSensorClient;
	
	@Autowired 
	private EventManager ids;
	
	public void addEvent(String categoryEnum, String detectionPointId){
		DetectionPoint detectionPoint = new DetectionPoint(categoryEnum, detectionPointId); 
		ids.addEvent(new Event(UserUtils.getAppsensorUser(), detectionPoint, getDetectionSystem()));
	}
	
	public void addAttack(String categoryEnum, String detectionPointId){
		DetectionPoint detectionPoint = new DetectionPoint(categoryEnum, detectionPointId); 
		ids.addAttack(new Attack(UserUtils.getAppsensorUser(), detectionPoint, getDetectionSystem()));
	}
	
	
	private DetectionSystem getDetectionSystem() {
    	return new DetectionSystem(
    			appSensorClient.getConfiguration().getServerConnection().getClientApplicationIdentificationHeaderValue());
    }
}
