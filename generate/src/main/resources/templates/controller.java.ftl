package ${package.Controller};

import org.springframework.beans.factory.annotation.Autowired;
import com.tjnu.losca.core.ResultJson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ${package.Entity}.${entity};
import javax.annotation.Resource;
import com.tjnu.losca.service.${table.serviceName};
import com.baomidou.mybatisplus.core.metadata.IPage;


<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName?length gt 0>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
    @Autowired
    ${table.serviceName} service;
    @GetMapping("/list")
    ResultJson<IPage> list(int pageNo, int pageSize, String value) {
        return ResultJson.success(service.list(pageNo,pageSize,value));
 }
    @GetMapping("/check")
    ResultJson<Boolean> check(Integer id, String value) {
        return ResultJson.success(service.check(id,value));
  }
    @PostMapping("/add")
    ResultJson<Boolean> add(${entity} entry) {
        return ResultJson.success(service.save(entry),"添加成功");
   }
    @GetMapping("/getone")
    ResultJson<${entity}> getone(Integer id) {
        return ResultJson.success(service.getById(id));
   }
    @PostMapping("/update")
    ResultJson<Boolean> update(${entity} entry) {
        return ResultJson.success(service.updateById(entry),"修改成功");
    }
    @PostMapping("/del")
    ResultJson<Boolean> del(${entity} entry) {
        return ResultJson.success(service.updateById(entry),entry.getActive() == 0 ? "删除成功" : "恢复成功");
     }

}
</#if>
