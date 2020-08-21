package dong.mirae.one.Controller;


import dong.mirae.one.DTO.DataDTO;
import dong.mirae.one.Entity.DataEntity;
import dong.mirae.one.Service.DataService;
import dong.mirae.one.Service.FileUploadDownService;
import lombok.extern.java.Log;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


@Controller
@Log
public class DataController {
    private DataService dataService;
    private FileUploadDownService fileUploadDownService;
    private String fileDownloadUri;

    public DataController(DataService dataService, FileUploadDownService fileUploadDownService){
        this.dataService = dataService;
        this.fileUploadDownService = fileUploadDownService;
    }

    @GetMapping("/data")
    public String data(Model model){
        List<DataEntity> list = dataService.findByAll();

        for(DataEntity x : list){
            if(x.getData_title().length() > 10)
                x.setData_title(x.getData_title().substring(0,11) + "...");
        }

        model.addAttribute("datas",list);
        model.addAttribute("adminstat", 0); //여기를 멤버 admin을 가져와야함
        return "data";
    }

    //, headers = ("content-type=multipart/*")

    @PostMapping("/upload")
    public String upload(DataDTO dataDTO) {
        dataDTO.setData_path(fileDownloadUri);
        dataService.save(dataDTO);
        return "redirect:/data";
    }

    @PostMapping("/file")
    public @ResponseBody
    String file(@RequestParam(value = "file") MultipartFile file) throws Exception {
        String fileName = fileUploadDownService.storeFile(file);

        fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        return "ok";
    }

    @GetMapping("/download")
    public ResponseEntity<String> download(@RequestParam(value = "number")int number){
        DataEntity dataEntity = dataService.findByData_id(number);
        return ResponseEntity.ok(dataEntity.getData_path());
    }


    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> download(@PathVariable("fileName")String fileName, HttpServletRequest request) throws Exception {
        Resource resource = fileUploadDownService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
