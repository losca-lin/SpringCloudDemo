package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

   public boolean check(Integer id, String value) {
      QueryWrapper<${entity}> wrapper = new QueryWrapper<${entity}>();
      wrapper.eq("name",value);
      if(id != null) {
         wrapper.ne("id", id);
      }
      return this.count(wrapper) == 0;
 }


   public IPage<${entity}> list(int pageNo, int pageSize, String value) {
      QueryWrapper<${entity}> wrapper = new QueryWrapper<${entity}>();
      if(StringUtils.isNotBlank(value)) {
         wrapper.like("name",value);
      }
      return this.page(new Page<${entity}>(pageNo,pageSize),wrapper);
 }
}
</#if>
