package cn.e3mall.search.listener;

import cn.e3mall.search.service.impl.SearchItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @Date 2018/6/8 21:47
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.search.listener
 * @ClassName: ItemChangeListener
 * @Description:
 *
 */
public class ItemChangeListener implements MessageListener{

    @Autowired
    private SearchItemServiceImpl searchItemServiceImpl;

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = null;
            Long itemId = null;
            if (message instanceof TextMessage) {
                textMessage = (TextMessage) message;
                itemId = Long.parseLong(textMessage.getText());
            }
            searchItemServiceImpl.addDocument(itemId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
