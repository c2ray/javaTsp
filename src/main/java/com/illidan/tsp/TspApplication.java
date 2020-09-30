package com.illidan.tsp;


import com.illidan.tsp.view.TspView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import de.felixroske.jfxsupport.SplashScreen;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 主启动类
 *
 * @author Illidan
 */
@SpringBootApplication
public class TspApplication extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {
        // 取消初始动画
        SplashScreen splash = new SplashScreen() {
            @Override
            public boolean visible() {
                return false;
            }
        };
        launch(TspApplication.class, TspView.class, splash, args);
    }

    /**
     * 自定义主窗口
     *
     * @param stage 主窗口
     * @param ctx   上下文环境
     */
    @Override
    public void beforeInitialView(Stage stage, ConfigurableApplicationContext ctx) {
        // 让窗体的标题栏消失
        // stage.initStyle(StageStyle.TRANSPARENT);
    }


    // @Override
    // public Collection<Image> loadDefaultIcons() {
    // 	return Collections.singletonList(new Image(getClass().getResource("/icon.png").toExternalForm()));
    // }

}
