package test;

/**
 * @author beimo
 * @date 2020/8/26
 */
public abstract class AbstractKaBindInHandler {

    public void process(BindInContext context) {
        AbstractKaBindInHandler handler = getBindInHandler(context);
        if (handler != null) {
            handler.doProcess(context);
            return;
        }
        doProcess(context);
    }

    public void doProcess(BindInContext context) {
        checkSign(context);
        checkParam(context);
        //0、直接从request获取 1、通过promethes获取 2、子类实现
        getTId(context);
        //0-直接从三方获取获取 1、三方等级直接取默认值1  2、子类实现
        getMemberType(context);

        if (!checkBind(context)) {
            //0-不初始化  1-初始化
            initKaUser(context);
            return;
        }

        //0-需要更新 1-不需要更新
        if (needUpdateModel(context)) {
            updateUserModel(context);
        }
    }

    private void checkSign(BindInContext context) {

    }

    public void checkParam(BindInContext context) {

    }

    public void getTId(BindInContext context) {

    }

    public void getMemberType(BindInContext context) {

    }

    public boolean checkBind(BindInContext context) {
        return false;
    }

    public void initKaUser(BindInContext context) {

    }

    public boolean needUpdateModel(BindInContext context) {
        return false;
    }

    public void updateUserModel(BindInContext context) {

    }

    public AbstractKaBindInHandler getBindInHandler(BindInContext context) {
        return new ABindInHandler();
    }
}
