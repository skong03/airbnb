/**
 * question? what's the location mean? current army location?  can you give some example?
 * Input (army_name, location, action())

action() could be:

move(new_location) then army_name to new_location, If there is an army at new_location, then company strength of two aramy:
The army have higher strength stay at new_location, the lower army is disappeared.
If two army have the same strength, both are disppeared.
hold() stay at the same location
support(army_name) supoort another army. The supported army have one more strength.
The army's strength are intialized as the same.

 */

import java.util.*;
public class SimulateDiplomacy {
    public static void main(String[] args) {
        Game game=new Game();
        Army a=new Army(game, "A", 6);
        game.addArmy(a,10);
        
        game.addArmy(new Army(game, "B", 5), 11);
        game.addArmy(new Army(game, "C", 6), 13);
        
        game.print();
        
        Move move=new Move(game);
        game.play(a.name, 9, move);
        game.print();
        
        //C support B
        Support s=new Support(game, "B");
        game.play("C", -1, s);
        
        //System.out.println(game.armyMap.get("B").getStrength());
        
        //move A to B's location, which will mark A B both disappear. 
        game.play(a.name, 11, move);
        game.print();
        
        
      }
}

class Game{
    // quick look for army. 
    public HashMap<String, Army> armyMap= new HashMap<>();
    
    // store the amry on board, if not found, the army disppeared. 
    public HashMap<Integer, Army> locationArmyMap=new HashMap<>();
    public HashMap<Army, Integer> armyLocationMap=new HashMap<>();
    
    public void addArmy(Army a, int location){
      this.armyMap.put(a.name, a);
      locationArmyMap.put(location, a);
      armyLocationMap.put(a, location);
    }
    
    public void play(String army, int location, Action action){
      action.action(army, location);
    }
    
    public void print(){
      for(int i: locationArmyMap.keySet()){
        Army a=locationArmyMap.get(i);
        System.out.println("location: "+ i+", army: "+ a.name);
      }
      System.out.println();
    }
  }
  
  interface Action{
    public void action(String army, int location);
  }
  
  //seems like move is stateless, can be re-use
  class Move implements Action{
    int newLocation;
    Game game;
    
    public Move(Game g){
      this.game=g;
    }
    @Override
    public void action(String armyName, int location){
      this.newLocation=location;
      Army cur=game.armyMap.get(armyName);
      //no army on the target location
      if(!game.locationArmyMap.containsKey(newLocation)){
        updateLocation(cur);
        return;
      }
     
      Army targetArmy = game.locationArmyMap.get(newLocation);
      if(cur.strength>targetArmy.getStrength()){
         //question? if move army, does it affect current support?
        remove(targetArmy);
        updateLocation(cur);
      }else if(cur.strength<targetArmy.getStrength()){
        remove(cur);
      }else{
        remove(targetArmy);
        remove(cur);
      }
    }
    
    private void updateLocation(Army cur){
      int curLocation=game.armyLocationMap.get(cur);
      game.locationArmyMap.remove(curLocation);
      game.locationArmyMap.put(newLocation, cur);
      game.armyLocationMap.put(cur, newLocation);
    }
    
    private void remove(Army a){
      int location = game.armyLocationMap.get(a);
      game.locationArmyMap.remove(location);
      game.armyLocationMap.remove(a);
    }
  }
  
  class Hold implements Action{
    //location not used
    @Override
    public void action(String armyName, int location){
    }
  }
  
  //support target
  class Support implements Action {
    String targetArmy;
    Game game;
    //question? support army might disappear. 
    public Support(Game g, String targetArmy){
      this.targetArmy=targetArmy;
      game=g;
    }
    
    //location not used
    @Override
    public void action(String armyName, int location){
      this.game.armyMap.get(targetArmy).supported.add(armyName);
    }
  }
  
  class Army{
    String name;
    int strength;
    // support army might display. so use int can't update all time.
    List<String> supported;
    Game game;
    
    public Army(Game g, String name, int strength){
      this.name=name;;
      this.strength = strength;   
      supported=new ArrayList<>();
      this.game=g;
    }
    
    public int getStrength(){
      int supportStrength=0;
      for(String s:supported){
        Army army=game.armyMap.get(s);
        if(game.armyLocationMap.containsKey(army)){
          supportStrength++;
        }
      }
      
      return supportStrength+this.strength;
    }
  }
  