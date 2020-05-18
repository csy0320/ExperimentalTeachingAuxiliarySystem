package cn.jjdcn.etas.fdfs.controller;

import cn.jjdcn.etas.common.bean.Result;
import cn.jjdcn.etas.fdfs.auth.annotation.CheckAuth;
import cn.jjdcn.etas.fdfs.entity.Picture;
import cn.jjdcn.etas.fdfs.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * (Picture)表控制层
 *
 * @author jjdcn
 * @since 2020-03-07 23:29:33
 */
@Slf4j
@RestController
@RequestMapping("fdfs/picture")
public class PictureController {
	/**
	 * 服务对象
	 */
	@Autowired
	private PictureService pictureService;


	/**
	 * 通过主键查询单条数据
	 *
	 * @param id 主键
	 * @return 单条数据
	 */
	@GetMapping("get/{id}")
	public Result getById(@PathVariable Integer id) {
		Picture picture = pictureService.queryById(id);
		if (picture != null) {
			Map<String,Picture> map = new HashMap<>();
			map.put("picture", picture);
			return Result.ok().data(map);
		}else {
			return Result.error().message("没有数据");
		}
	}

	/**
	 * 通过ids查询单条数据
	 *
	 * @param ids
	 * @return 单条数据
	 */
	@GetMapping("get/ids")
	public List<Picture> doQueryPictureByIds(@RequestParam("ids") String ids) {
		log.info("ids:{}",ids);
		List<Integer> idss = splitIdsString(ids);
		if (idss.size() == 0) return new ArrayList<>();

		List<Picture> pictures = pictureService.queryByIds(idss);
		if (pictures != null) {
			return pictures;
		}else {
			return new ArrayList<>();
		}
	}

	private List<Integer> splitIdsString(String ids) {
		List<String> strings = Arrays.asList(ids.split("-"));
		List<Integer> idss = new ArrayList<>();
		for (int i = 0; i < strings.size(); i++) {
			if("".equals(strings.get(i))) continue;
			idss.add((Integer.valueOf( strings.get(i))));
		}
		return idss;
	}

	/**
	 * 获得所有数据
	 *
	 * @return
	 */
	@GetMapping("get")
	public Result getAll() {

		List<Picture> pictures = pictureService.queryAllByLimit(0, 100);
		if (pictures != null && !pictures.isEmpty()) {
			Map<String,List> map = new HashMap<>();
			map.put("pictures",pictures);
			return Result.ok().data(map);
		} else {
			return Result.error().message("没有数据");
		}
	}

	/**
	 * 添加一条数据
	 *
	 * @param picture
	 * @return
	 */
	@PostMapping("add")
	@CheckAuth
	public Result add(@RequestBody Picture picture) {

		Picture added = pictureService.insert(picture);
		if (added != null) {
			Map<String,Picture> map = new HashMap<>();
			map.put("picture",picture);
			return Result.ok().message("添加成功").data(map);
		} else {
			return Result.error().message("添加失败");
		}
	}

	/**
	 * 更新数据
	 *
	 * @param picture
	 * @return
	 */
	@PutMapping("update")
	@CheckAuth
	public Result update(@RequestBody Picture picture) {
		Picture updated = pictureService.update(picture);
		if (updated != null) {
			Map<String,Picture> map = new HashMap<>();
			map.put("picture",updated);
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
	@CheckAuth
	public Result delete(@PathVariable Integer id) {
		log.info("delete image ID:{}",id);
		if (pictureService.deleteById(id)) {
			return Result.ok().message("删除成功");
		} else {
			return Result.error().message("删除失败");
		}
	}

}