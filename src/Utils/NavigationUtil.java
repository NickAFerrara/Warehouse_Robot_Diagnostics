package Utils;
import java.util.ArrayList;

import DomainObjects.Coordinates;

public class NavigationUtil {
    
    public static ArrayList<Coordinates> moveUtil(int startX, int startY, int endX, int endY){

        Coordinates startPoint = new Coordinates(startX, startY);
        Coordinates endPoint = new Coordinates(endX, endY);

        //Calculate the number of steps in X and Y direction to move from start coordinate to end coordinate
        int numXSteps = countSteps(startPoint.getX(), endPoint.getX()), numYSteps = countSteps(startPoint.getY(), endPoint.getY());;
        
        //Create list of coordinates for steps from start to end
        ArrayList<Coordinates> steps = new ArrayList<>();
        
        //Add X direction movements to list
        if(startPoint.getX() < endPoint.getX()){
        
            steps.add(new Coordinates(startPoint.getX() + 1, startPoint.getY()));
                    
            for(int i = 0; i < numXSteps - 1; i++){
                steps.add(new Coordinates(steps.get(steps.size() - 1).getX() + 1, steps.get(steps.size() - 1).getY()));
            }
        
        } else {
        
            steps.add(new Coordinates(startPoint.getX() - 1, startPoint.getY()));
                    
            for(int i = 0; i < numXSteps - 1; i++){
                steps.add(new Coordinates(steps.get(steps.size() - 1).getX() - 1, steps.get(steps.size() - 1).getY()));
            }
        
        }
        
        //Add Y direction movements to list
        if(startPoint.getY() < endPoint.getY()){
        
            steps.add(new Coordinates(steps.get(steps.size() - 1).getX(), steps.get(steps.size() - 1).getY() + 1));
                    
            for(int i = 0; i < numYSteps - 1; i++){
                steps.add(new Coordinates(steps.get(steps.size() - 1).getX(), steps.get(steps.size() - 1).getY() + 1));
            }
        
        } else {
        
            steps.add(new Coordinates(steps.get(steps.size() - 1).getX(), steps.get(steps.size() - 1).getY() - 1));
                    
            for(int i = 0; i < numYSteps - 1; i++){
                steps.add(new Coordinates(steps.get(steps.size() - 1).getX(), steps.get(steps.size() - 1).getY() - 1));
            }
        }

        return steps;
    }

    //method for counting number of steps required for movement
    public static int countSteps(int start, int end){
        int stepCount = 0;

        if(start < end){
            for(int i = start; i < end; i++){
                stepCount++;
            }
        } else {
            for(int i = end; i < start; i++){
                stepCount++;
            }
        }

        return stepCount;
    }

}
