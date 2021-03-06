package cn.jjdcn.soa.etas.controller;

import cn.jjdcn.soa.etas.bean.Result;
import cn.jjdcn.soa.etas.entity.Disease;
import cn.jjdcn.soa.etas.pojo.dto.DiseaseDto;
import cn.jjdcn.soa.etas.pojo.vo.DiseaseVO;
import cn.jjdcn.soa.etas.service.DiseaseService;
import cn.jjdcn.soa.etas.service.SettingService;
import cn.jjdcn.soa.etas.utils.annotation.CheckAuth;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 植病表(Disease)表控制层
 *
 * @author jjdcn
 * @since 2020-03-09 14:14:29
 */
@Slf4j
@RestController
@RequestMapping("api/manage/disease")
public class DiseaseController {
    /**
     * 服务对象
     */
    @Autowired
    private DiseaseService diseaseService;

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
		DiseaseVO diseaseVO = diseaseService.queryById(id);
		if (diseaseVO != null) {
			Map<String, DiseaseVO> map = new HashMap<>();
			map.put("item", diseaseVO);
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

		List<Disease> diseases = diseaseService.queryAll();
		if (diseases != null && !diseases.isEmpty()) {
			Map<String,List> map = new HashMap<>();
			map.put("diseases",diseases);
			return Result.ok().data(map);
		} else {
			return Result.error().message("没有数据");
		}
	}

	/**
	 * 获得所有数据
	 *
	 * @return
	 */
	@PostMapping("getByFilter")
	public Result getAll(@RequestBody DiseaseDto dto, @RequestParam("pagesize") Integer pageSize, @RequestParam("pagecount") Integer pageCount ) {
		if ( pageSize == null ){
			pageSize = settingService.queryByUserId(1).getPageSize();
		}
		log.info("pageSize:{} pageCount:{}",pageSize,pageCount);
		int total = diseaseService.countAllByFilter(dto);
		Page<Object> objects = PageHelper.startPage(pageCount, pageSize);
		List<Disease> diseases = diseaseService.queryAllByFilter(dto);
		if (diseases != null) {
			HashMap<String, Object> map = Maps.newHashMap();
			map.put("items",diseases);
			map.put("total",total);
			map.put("pageSize",pageSize);
			return Result.ok().data(map);
		} else {
			return Result.error().message("请求错误");
		}
	}

	/**
	 * 添加一条数据
	 *
	 * @param diseaseDto
	 * @return
	 */
	@CheckAuth
	@PostMapping("add")
	public Result add(@Valid @RequestBody DiseaseDto diseaseDto) {

		log.info("diseaseDto: {}", diseaseDto);
		Disease added = diseaseService.insert(diseaseDto);
		if (added != null) {
			Map<String,Disease> map = new HashMap<>();
			map.put("disease",added);
			return Result.ok().message("添加成功").data(map);
		} else {
			return Result.error().message("添加失败");
		}
	}

	/**
	 * 更新数据
	 *
	 * @param diseaseDto
	 * @return
	 */
	@CheckAuth
	@PutMapping("update")
	public Result update(@RequestBody DiseaseDto diseaseDto) {
		int update = diseaseService.update(diseaseDto);
		if (update > 0) {
			return Result.ok().message("更新成功");
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
	@CheckAuth
	@DeleteMapping("delete/{id}")
	public Result delete(@PathVariable Integer id) {
		if (diseaseService.deleteById(id)) {
			return Result.ok().message("删除成功");
		} else {
			return Result.error().message("删除失败");
		}
	}


//	/**
//	 * 通过主键查询单条数据
//	 *
//	 * @param id 主键
//	 * @return 单条数据
//	 */
//	@GetMapping("get/{id}")
//	public Disease getById(@PathVariable Integer id) {
//		DiseaseVO diseaseVO = diseaseService.queryById(id);
//		if (diseaseVO != null) {
//			Map<String, DiseaseVO> map = new HashMap<>();
//			map.put("item", diseaseVO);
//			return Result.ok().data(map);
//		}else {
//			return Result.error().message("没有数据");
//		}
//	}

	/**
	 * 获得所有数据给API
	 *
	 * @return
	 */
	@GetMapping("api/get")
	public List<Disease> getAllDisease() {

		List<Disease> diseases = diseaseService.queryAll();
		if (diseases != null && !diseases.isEmpty()) {
			return diseases;
		} else {
			return null;
		}
	}
}