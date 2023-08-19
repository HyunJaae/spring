package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TimeTraceApp {

    /**
     * 시간 측정 AOP
     * <pre>
     * 적용 범위 설정 시 !target(hello.hellospring.config.SpringConfig) 을
     * 추가하지 않으면 순환 참조가 발생한다.
     * 그 이유는 AOP 적용 범위를 hello.hellospring 디렉토리 전체로 잡게 되면
     * SpringConfig 클래스의 TimeTraceApp 클래스를 빈 등록하는 메소드도 포함되는데
     * 해당 메소드가 바로 자기 자신인 TimeTraceApp 을 생성하기 때문이다.
     * </pre>
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* hello.hellospring..*(..)) && !target(hello.hellospring.config.SpringConfig)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }

}
