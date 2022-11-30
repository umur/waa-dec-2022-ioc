
@MyBean
public class ConsumerClass {
    @MyAutowired
    private ApplicationLevelClass applicationLevelClass;

//    public ConsumerClass(ApplicationLevelClass ac){
//        this.applicationLevelClass = applicationLevelClass;
//    }

    public void print(){
        //ac.print();
        System.out.println("Hello Gilbert from ConsumerClass!");
        applicationLevelClass.print();
    }
}
