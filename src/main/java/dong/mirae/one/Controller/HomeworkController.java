package dong.mirae.one.Controller;

import dong.mirae.one.DTO.HomeworkDTO;
import dong.mirae.one.Entity.HomeworkEntity;
import dong.mirae.one.Service.HomeworkService;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class HomeworkController {
    private HomeworkService service;
    private JavaMailSender mailSender;

    @GetMapping("/homework")
    public String homeworkPage(Model model){
        List<HomeworkEntity> entity = service.findBetween();
        model.addAttribute("homeworkList", entity);
        return "homework";
    }

    @PostMapping("/homework")
    public @ResponseBody
    boolean homework(@RequestParam("file") MultipartFile file) throws IOException{
        if(file == null){
            return false;
        }else{
            List<HomeworkEntity> list = service.findAll();
            HomeworkEntity entity = list.get(0);
            String originalName = file.getOriginalFilename();
            String originalExtension = originalName.substring(originalName.lastIndexOf("."));;
            String storage = UUID.randomUUID().toString().replaceAll("-","") + originalExtension;
            File tempfile = new File("/home/ubuntu/uploadfile/tempfile/" + storage);

            file.transferTo(tempfile);

            MailThread mailThread = new MailThread(tempfile, entity); //tempfile로 해보기.
            mailThread.start();

            return true;
        }
        /*
        final MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                helper.setFrom("dbdlstjq960@naver.com");
                helper.setTo(entity.getReceive_mail()); //member에서 이메일 받아오기
                helper.setSubject(entity.getHomework_title() + "의 과제입니다."); // 제목
                if(file.getOriginalFilename() != null){
                    helper.addAttachment(file.getOriginalFilename(),file); //확장자까지 받아와야함..
                }else{
                    helper.setText("파일을 올리지 않았거나 오류가 났습니다.");
                }
                helper.setText("과제입니다."); //내용
            }
        };
        mailSender.send(preparator);
        */
    }

    @PostMapping("/homework_saveing")
    public @ResponseBody
    boolean save(HomeworkDTO homeworkDTO) {
        service.save(homeworkDTO);
        return true;
    }


    private class MailThread extends Thread {
        private File file;
        private HomeworkEntity entity;

        public MailThread(File file, HomeworkEntity entity){
            this.file = file;
            this.entity = entity;
        }

        private void sendmail(){
            final MimeMessagePreparator preparator = new MimeMessagePreparator() {
                @Override
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                    helper.setFrom("dbdlstjq960@naver.com");
                    helper.setTo(entity.getReceive_mail());
                    helper.setSubject(entity.getHomework_title() + "의 과제입니다."); // 제목
                    if(file != null){
                        helper.addAttachment(file.getName(),file); //확장자까지 받아와야함..
                    }else{
                        helper.setText("파일을 올리지 않았거나 오류가 났습니다.");
                    }
                    helper.setText("과제입니다."); //내용
                }
            };
            mailSender.send(preparator);
            file.delete();
        }

        @Override
        public void run() {
            sendmail();
        }
    }
}
