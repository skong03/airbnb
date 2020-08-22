

import java.util.*;
class Solution3 {
  
    public static void main(String[] args) {
      FileSystem fs=new FileSystem();
      try{
        fs.create("/a", 1);
        fs.println(fs.get("/a"));
        fs.create("/a/b", 2);
        fs.println(fs.get("/a/b"));
        fs.create("/c/d", 1);
      }catch(Exception e){
        System.out.println(e.getMessage());
      }
      
    }
   
  }
// create("/a",1)
// get("/a") //得到 1
// create("/a/b",2)
// get("/a/b") //得到 2
// create("/c/d",1) //Error,因为它的上一级“/c”并不存在
// get("/c") //Error,因为“/c”不存在
class FileSystem {
    private Folder root;
    public FileSystem(){
      root=new Folder();
    }
    
    public void create(String path, int v) throws Exception{
      path=path.substring(1, path.length());
      String[] paths=path.split("/");
      Folder fo=root;
      for(int i=0;i<paths.length-1;i++){
        if(fo.subFolders.containsKey(paths[i])){
          fo=fo.subFolders.get(paths[i]);
        }else{
          throw new Exception(paths[i]+" doesn't exists"); 
        }
      }
      
      String last=paths[paths.length-1];
      
      if(!fo.subFolders.containsKey(last)){
        fo.subFolders.put(last, new Folder());
      }
      
      Folder cur=fo.subFolders.get(last);
      cur.files.add(new File(v));
      
    }
    
    public List<Integer> get(String path){
      path= path.substring(1, path.length());
      String[] paths = path.split("/");
      Folder fo=root;
      for(int i=0;i< paths.length;i++){
        if(fo.subFolders.containsKey(paths[i])){
          fo=fo.subFolders.get(paths[i]);
          if(fo.funcs.size()>0){
            for(Runnable f: fo.funcs){
              f.run();
            }
          }
        }else{
          return null;
        }
        
      }
      
      List<Integer> result = new ArrayList<Integer>();
      for(File f: fo.files){
        result.add(f.i);
      }
      return result;
    }
    
    public void watch(String path, Runnable func) throws Exception{
      path = path.substring(1, path.length());
      String[] paths=path.split("/");
      Folder fo=root;
      for(int i=0;i<paths.length;i++){
         if(fo.subFolders.containsKey(paths[i])){
          fo=fo.subFolders.get(paths[i]);
        }else{
          throw new Exception(paths[i]+" doesn't exists"); 
        }
      }
      
      fo.funcs.add(func);
      
    }
    
    public void println(Collection<Integer> c){
      for(int i: c){
        System.out.println(i);
      }
    }
  }
  
  class FileObject{}
  
  class File extends FileObject{
    public int i;
    public File(int i){
      this.i=i;
    }
  }
  
  class Folder extends FileObject{
    public List<File> files;
    public HashMap<String, Folder> subFolders;
    public List<Runnable> funcs;
    
    public Folder(){
      funcs=new ArrayList<Runnable>();
      files=new ArrayList<File>();
      subFolders = new HashMap<>();
    }
  }
  