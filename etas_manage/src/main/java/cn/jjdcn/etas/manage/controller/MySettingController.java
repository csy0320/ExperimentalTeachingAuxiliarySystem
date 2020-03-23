package cn.jjdcn.etas.manage.controller;

import cn.jjdcn.etas.common.bean.Result;
import cn.jjdcn.etas.manage.entity.Setting;
import cn.jjdcn.etas.manage.service.SettingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Setting)表控制层
 *
 * @author jjdcn
 * @since 2020-03-13 21:52:45
 */
@Slf4j
@RestController
@RequestMapping("manage/setting")
public class MySettingController {
    /**
     * 服务对象
     */
    @Autowired
    private SettingService settingService;
    
    
    /**
	 * 通过主键查询单条数据
	 *
	 * @param id 主键
	 * @return 单条数据
	 */
	@GetMapping("get/{id}")
	public Result getById(@PathVariable Integer id) {
		Setting setting = settingService.queryById(id);
		if (setting != null) {
			Map<String,Setting> map = new HashMap<>();
			map.put("setting", setting);
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

		List<Setting> settings = settingService.queryAllByLimit(0, 100);
		if (settings != null && !settings.isEmpty()) {
			Map<String,List> map = new HashMap<>();
			map.put("settings",settings);
			return Result.ok().data(map);
		} else {
			return Result.error().message("没有数据");
		}
	}

	/**
	 * 添加一条数据
	 *
	 * @param setting
	 * @return
	 */
	@PostMapping("add")
	public Result add(@RequestBody Setting setting) {

		Setting added = settingService.insert(setting);
		if (added != null) {
			Map<String,Setting> map = new HashMap<>();
			map.put("setting",setting);
			return Result.ok().message("添加成功").data(map);
		} else {
			return Result.error().message("添加失败");
		}
	}

	/**
	 * 更新数据
	 *
	 * @param setting
	 * @return
	 */
	@PutMapping("update")
	public Result update(@RequestBody Setting setting) {
		setting.setUserId(1);// 1管理员
		Setting updated = settingService.update(setting);
		if (updated != null) {
			Map<String,Setting> map = new HashMap<>();
			map.put("setting",updated);
			return Result.ok().message("更新成功").data(map);
		} else {
			return Result.error().message("更新失败");
		}
	}

	/**
	 * 用户提供pageSize更新页面大小,不需要提供userID
	 *
	 * @param setting
	 * @return
	 */
	@PutMapping("updateByUserId")
	public Result updateByUserId(@RequestBody Setting setting) {
		setting.setUserId(1);// 1管理员
		log.info("setting:{}",setting);
		Setting updated = settingService.updateByUserId(setting);
		if (updated != null) {
			Map<String,Setting> map = new HashMap<>();
			map.put("setting",updated);
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
		if (settingService.deleteById(id)) {
			return Result.ok().message("删除成功");
		} else {
			return Result.error().message("删除失败");
		}
	}

}