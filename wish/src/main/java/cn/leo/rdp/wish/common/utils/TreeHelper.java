package cn.leo.rdp.wish.common.utils;

import cn.leo.rdp.base.entity.SysMenu;
import cn.leo.rdp.common.constant.RdpConstants;
import cn.leo.common.entity.AuthTreeEntity;
import cn.leo.common.entity.TreeEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * tree grid 的组件需要后端排序 如部门、菜单列表
 * @author leo
 *
 */
public class TreeHelper<T extends TreeEntity<String>> {

	/**
	 * 默认跟节点
	 */
	public static final String DEFAULT_PARENTID = "0";

	/**
	 * eg --- --- --- ----
	 * 
	 * @param parentId
	 *            从哪里开始排
	 * @return
	 */
	public List<T> treeGridList(List<T> list) {
		List<T> result = this.nextLevelTreeList(list, DEFAULT_PARENTID);
		//没有匹配到的，如父节点不是0 的； 搜索场景
		for(T t :list) {
			if (!result.contains(t)) {
				result.add(t);
			}
		}
		return result;
	}
	
	private List<T> nextLevelTreeList(List<T> list, String parentId){
		List<T> result = new ArrayList<>();
		for (T t : list) {//
			if (parentId.equals(t.getParentId())) {
				// 从跟节点开始
				result.add(t);
				result.addAll(this.nextLevelTreeList(list, t.getId()));
			}
		}
		return result;
	}

	/**
	 * 处理parentid
	 * @param t
	 * @param parent
	 */
	public void setParent(T t,T parent) {
		if (StringUtils.isEmpty(t.getParentId())) {
			t.setParentId(DEFAULT_PARENTID);
			t.setParentIds(DEFAULT_PARENTID + RdpConstants.SEPARATOR);
		} else {
			if (null != parent) {
				t.setParentId(parent.getId());
				t.setParentIds(parent.getParentIds() + RdpConstants.SEPARATOR + parent.getId());
			}
		}
	}
	

	public List<AuthTreeEntity> authTreeList(List<SysMenu> list, List<SysMenu> roleAuths) {
		Map<String, String> authMap = getRoleAuthMap(roleAuths);
		List<AuthTreeEntity> result = this.nextAuthTreeList(list, DEFAULT_PARENTID, authMap);
		return result;
	}
	
	private List<AuthTreeEntity> nextAuthTreeList(List<SysMenu> list, String parentId, Map<String, String> authMap){
		List<AuthTreeEntity> result = new ArrayList<>();
		for (SysMenu t : list) {//
			if (parentId.equals(t.getParentId())) {
				// 从跟节点开始
				AuthTreeEntity ate = new AuthTreeEntity();
				ate.setName(t.getName());
				ate.setValue(t.getId());
				ate.setChecked(authMap.get(t.getId())==null?false:true);
				if(!t.getType().equals("999")) {
					ate.setList(this.nextAuthTreeList(list, t.getId(), authMap));
				}
				result.add(ate);
			}
		}
		return result;
	}
	
	private Map<String, String> getRoleAuthMap(List<SysMenu> roleAuths){
		Map<String, String> authMap = new HashMap<String, String>();
		if(!CollectionUtils.isEmpty(roleAuths)) {
			for(SysMenu menu : roleAuths) {
				authMap.put(menu.getId(), menu.getId());
			}
		}
		return authMap;
	}
}
