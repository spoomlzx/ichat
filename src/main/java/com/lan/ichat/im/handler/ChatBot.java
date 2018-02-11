package com.lan.ichat.im.handler;

import com.lan.common.util.StringUtils;
import com.lan.ichat.im.push.MessagePusher;
import org.spoom.im.sdk.server.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * package com.lan.ichat.im.handler
 *
 * @author spoomlan
 * @date 03/02/2018
 */
@Component
public class ChatBot {
    @Autowired
    private MessagePusher messagePusher;

    private String[] texts = new String[]{
            "反正她叫什么不重要",
            "给她这个名字",
            "是我在设想她的家庭",
            "她的父母",
            "她那样的家庭背景会给她取什么样的名字",
            "什么样的家庭呢",
            "父亲是个文人，做过画报社编辑，写点儿散文编点儿剧本，没怎么大成名",
            "她的母亲呢，长相是好看的，剧团里打扬琴弹古筝，像所有可爱女人有着一点儿恰到好处的俗，也像 她们一样略缺一点儿脑筋，因而过日常生活和政治生活都绝对随大溜",
            "我能想象在小嫚的母 亲跟她父亲闹离婚前，那个家庭里是温情的，小布尔乔亚的",
            "我也完全可以想象，善良软弱的文人父亲给小嫚取出这样一个名字",
            "何小嫚很有可能向着一个心智正常，不讨人嫌的女孩成长",
            "像所有软弱善良的人一样，小嫚的父亲是那种莫名地对所有人怀一点儿歉意的人",
            "隐约感觉他欠着所有人一点儿情分",
            "人们让他当坏分子，似乎就因为他比任何人都好说话",
            "常常漫不经意地吃亏，于是人们就想，何妨把坏分子的亏也让他吃了",
            "到了何小嫚的母亲都开 始讲他坏话，提出离婚的时候，他不再觉得心里苦，他反倒觉得解脱了",
            "睡前吃安眠药，",
            "他 心里一亮，看到了终极的出路",
            "这天早上妻子去上班了，他牵着女儿的手，送她去托儿所",
            "家门外不远，是个早点铺子，炸油条和烤大饼以及沸腾的豆浆",
            "那丰盛气味在饥荒年代显得 格外美，一条小街的人都以嗅觉揩油",
            "一出家门小嫚就说，好想好想吃一根油条",
            "四岁的小 嫚是知道的，父亲对所有人都好说话，何况对她",
            "父女俩单独在一块儿的时候，",
            "感情上到物 质上她都可以敲诈父亲一笔",
            "然而这天父亲身上连一根油条的钱都没有",
            "他跟早点铺掌柜 说，赊一根油条给孩子吃吧，一会儿就把钱送来",
            "爸爸蹲在女儿面前，享受着女儿的咀嚼，吞咽",
            "声音动作都大了点儿，胃口真好，也替父亲解馋了",
            "吃完，父亲用他折得四方的 花格手绢替女儿擦嘴，擦手",
            "手是一根手指一根手指地替她擦。擦一根手指，",
            "父女俩就对视 着笑一下",
            "那是小嫚记得的父亲的最后容貌",
            "第二年秋天，何小嫚也离开了我们",
            "她也是被处理下基层的",
            "一九七八年国庆，",
            "我们到 阿坝为即将解散的骑兵团和军马场演出",
            "战争不再需要骑兵和军马，骑兵和军马将永远退役",
            "我们的芭蕾小舞剧《军马和姑娘》也就将永远谢幕",
            "舞台坑洼不平，",
            "第一次走台Ａ角小 战士就崴了脚腕，脚肿得漫说穿足尖鞋，连四十号男鞋都穿不进去，",
            "把皮帽子当鞋穿",
            "杨老师便把何小嫚顶上去",
            "何小嫚那时已是标准龙套，只在两个大型集体舞里充数，",
            "因此所有人认为这段小战士独舞是对她的厚赏",
            "女分队队长郝淑雯在服装组找到了小嫚",
            "何小嫚因为担任的节目少，常在服装组帮忙，总有钉纽扣、补假发之类的琐事可做",
            "她当兵四年，",
            "到此刻 对于“进步”和“向组织靠拢”的真谛彻底摸透，那就是对该你做的事马虎，对不该你做的事操劳",
            "假如服装员跟团支部提出“何小嫚常常帮着服装组补连裤袜”，",
            "那可远比舞蹈分队表扬 她“何小嫚练功积极，演出认真”重要得多",
            "听到后者，团支部会认为，舞蹈队的，",
            "练功积极 是本职，演出认真理所当然，有什么可表扬的",
            "忙活别人的工作，",
            "比如帮服装员补鞋补袜之 类，就会捞到分外表扬",
            "郝淑雯向何小嫚传达完杨老师的指令，何小嫚说不行，",
            "她顶不了Ａ 角小战士",
            "郝淑雯以为自己听错了，平时在杨老师编导的舞蹈里，哪怕给她的角色是只狗，她都会乐颠颠地接过来演",
            "何小嫚说完，又把鼻尖凑到尼龙袜上，继续织补",
            "我们还有待发现，小嫚眼睛的精彩凝聚力得益于她的中度近视",
            "有次在昏暗的后台，她用扫把来回扫一小块儿地方：原来她把屋顶漏进来的白色光斑当黏在地板上的化妆棉纸清扫了。"
    };

    public void replyChatMessage(ChatMessage chatMessage) {
        ChatMessage reply = new ChatMessage();
        Random random = new Random();
        String content = texts[random.nextInt(texts.length)];
        reply.setMsgId(StringUtils.getUUID());
        reply.setBody(content);
        reply.setMsgFrom(chatMessage.getMsgTo());
        reply.setMsgTo(chatMessage.getMsgFrom());
        reply.setMsgType(ChatMessage.MessageType.MSG_TEXT);
        messagePusher.push(reply);
    }
}
