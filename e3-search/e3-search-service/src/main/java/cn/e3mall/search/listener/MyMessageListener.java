package cn.e3mall.search.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @Date 2018/6/8 20:29
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.search.listener
 * @ClassName: MyMessageListener
 * @Description: ActiveMQ消息监听器
 *
 */
public class MyMessageListener implements MessageListener{

    /**
     * @Date 2018/6/8 20:29
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: onMessage
     * @Params: [message]
     * @ReturnType: void
     * @Description: 监听方法
     *
     */
    @Override
    public void onMessage(Message message) {

        try {
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            System.out.println(text);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
