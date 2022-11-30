
@MyBean
public class ConsumerClass {
    @MyAutowired
    private ApplicationLevelClass ac;

//    public ConsumerClass(ApplicationLevelClass ac){
//        this.ac = ac;
//    }

    public void print(){
        //ac.print();
        System.out.println("Hello Gilbert from ConsumerClass!");
        ac.print();
    }
}
