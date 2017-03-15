import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

import java.util.Arrays;

public class Sample {

    public static void main(String[] args) {
        just();

//        from();

//        error();

//        defer();

//        map();

    }

    private static void just() {
        Observable.just("Hello")
                .subscribe(new Action1<String>() {
                    public void call(String s) {
                        System.out.print(s + " " + "World");
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        System.out.println("!");
                    }
                });
    }

    private static void from() {
        Observable.from(Arrays.asList("Hello", " ", "World"))
                .subscribe(s -> System.out.print(s), throwable -> {
                }, () -> System.out.println("!"));
    }

    private static void error() {
        Observable.error(new RuntimeException("Uh oh"))
                .subscribe(it -> System.out.println(it), throwable -> throwable.printStackTrace());
    }

    private static void defer() {
        Observable<String> just = Observable.just(printSomethingDumb());
//        Observable<String> defer = Observable.defer(() -> Observable.just(printSomethingDumb()));
//        Observable<String> fromCallable = Observable.fromCallable(Sample::printSomethingDumb);

//        defer.subscribe();
//        fromCallable.subscribe();
    }

    private static String printSomethingDumb() {
        String x = "Something dumb";
        System.out.println(x);
        return x;
    }

    private static void map() {
        Observable.just("Hello, World!")
                .map(it -> it.substring(0, it.indexOf(",")))
                .subscribe(System.out::println);
    }

}
