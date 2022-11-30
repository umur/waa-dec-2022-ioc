public class Main {
    public static void main(String[] args) throws BeanNotFoundException, IllegalAccessException {

        MyInjector inj = new MyInjector();

        //ApplicationLevelClass ac  = (ApplicationLevelClass) inj.getMyBean("ApplicationLevelClass");
        ConsumerClass cc  = (ConsumerClass) inj.getBean(ConsumerClass.class);

        //ApplicationLevelClass ac = new ApplicationLevelClass();
        //ConsumerClass cc = new ConsumerClass(ac);
        cc.print();

    }
}