package test;

/**
 * @author beimo
 * @date 2020/8/26
 */
public class ABindInHandler extends AbstractKaBindInHandler{

    @Override
    public void getTId(BindInContext context) {
        context.setTid("111");
    }
}
