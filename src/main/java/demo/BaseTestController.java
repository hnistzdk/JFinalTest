package demo;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.jfinal.render.FileRender;
import com.jfinal.upload.UploadFile;
import com.zdk.dao.UserMapper;
import com.zdk.pojo.User;
import com.zdk.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @Description
 * @Author zdk
 * @Date 2021/4/1 8:03
 */
@Path("/Test")
public class BaseTestController extends Controller {
    public void index() {
        String id=get("id");
        String username =get("username");
        String password=get("password");
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int count=mapper.addUser(new User(Integer.parseInt(id), username, password));
        sqlSession.commit();
        if(count>0){
            renderText("添加成功");
        }else {
            renderText("添加失败");
        }
        sqlSession.close();
    }
    public void test() {
        render("renderTest.html");
    }
    public void fileDownload(){
        renderFile("test.jpg");
    }
    public void testQrCode(){
        // 二维码携带的数据
        String data = "这是一个二维码";
        // 渲染二维码图片，长度与宽度为 200 像素
        renderQrCode(data, 200, 200);
        // 最后一个参数 'M' 为纠错级别
        //renderQrCode(data, 200, 200, 'M');
    }
    @Test
    public void fileUpload(){
        //报错  但实际上传成功

        UploadFile file = getFile();
        String uploadPath = file.getUploadPath();
        FileRender fileRender=new FileRender(uploadPath);
        fileRender.render();

        //以下代码也可运行上传
//        try {
//            UploadFile file = getFile();
//            System.out.println("--------file--------");
//            File delfile = new File(file.getUploadPath()+"\\"+file.getFileName());
//            System.out.println(file.getUploadPath());
//            System.out.println(file.getFileName());
//            System.out.println("=========="+delfile.getPath());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
