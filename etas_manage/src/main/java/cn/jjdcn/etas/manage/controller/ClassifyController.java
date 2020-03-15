package cn.jjdcn.etas.manage.controller;

import cn.jjdcn.etas.common.bean.Result;
import cn.jjdcn.etas.manage.entity.Classify;
import cn.jjdcn.etas.manage.service.ClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分类表(Classify)表控制层
 *
 * @author jjdcn
 * @since 2020-03-09 14:14:29
 */
@RestController
@RequestMapping("classify")
public class ClassifyController {
    /**
     * 服务对象
     */
    @Autowired
    private ClassifyService classifyService;
    
    
    /**
	 * 通过主键查询单条数据
	 *
	 * @param id 主键
	 * @return 单条数据
	 */
	@GetMapping("get/{id}")
	public Result getById(@PathVariable Integer id) {
		Classify classify = classifyService.queryById(id);
		if (classify != null) {
			Map<String,Classify> map = new HashMap<>();
			map.put("classify", classify);
			return Result.ok().data(map);
		}else {
			return Result.error().message("没有数据");
		}
	}

	/**
	 * 获得所有数据
	 *
	 * @return
	 */
	@GetMapping("get")
	public Result getAll() {

		List<Classify> classifys = classifyService.queryAllByLimit(0, 100);
		if (classifys != null && !classifys.isEmpty()) {
			Map<String,List> map = new HashMap<>();
			map.put("classifys",classifys);
			return Result.ok().data(map);
		} else {
			return Result.error().message("没有数据");
		}
	}

	/**
	 * 添加一条数据
	 *
	 * @param classify
	 * @return
	 */
	@PostMapping("add")
	public Result add(@RequestBody Classify classify) {

		Classify added = classifyService.insert(classify);
		if (added != null) {
			Map<String,Classify> map = new HashMap<>();
			map.put("classify",classify);
			return Result.ok().message("添加成功").data(map);
		} else {
			return Result.error().message("添加失败");
		}
	}

	/**
	 * 更新数据
	 *
	 * @param classify
	 * @return
	 */
	@PutMapping("update")
	public Result update(@RequestBody Classify classify) {
		Classify updated = classifyService.update(classify);
		if (updated != null) {
			Map<String,Classify> map = new HashMap<>();
			map.put("classify",updated);
			return Result.ok().message("更新成功").data(map);
		} else {
			return Result.error().message("更新失败");
		}
	}

	/**
	 * 根据id删除
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping("delete/{id}")
	public Result delete(@PathVariable Integer id) {
		if (classifyService.deleteById(id)) {
			return Result.ok().message("删除成功");
		} else {
			return Result.error().message("删除失败");
		}
	}

}