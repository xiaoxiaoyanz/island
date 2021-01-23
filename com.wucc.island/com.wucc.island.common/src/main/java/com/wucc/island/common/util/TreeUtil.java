package com.wucc.island.common.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanMap;

import com.wucc.island.common.annotation.TreeConfig;
import com.wucc.island.common.constant.consts.WhetherConsts;
import com.wucc.island.common.exception.BaseException;
import com.wucc.island.common.vo.TreeVO;

public class TreeUtil {

    private static final String PREFIX_SPACE = "  ";

    /**
     * 适用场景：查询树所有的节点，将数据封装为ant-tree要求的结构 要求：为了提高效率，本方法默认参数集合的数据是按照层级升序，所以调用时请处理好参数集合的排序
     * 优点：是只需要遍历一次即可，效率高；且可以接受任意泛型的集合参数，通用性好
     *
     * @param List
     *            数据集合
     * @return List<Map<Object, Object>> ant-tree树结构数据根节点集合
     * @author zhoujiej
     */
    @SuppressWarnings("unchecked")
    public static List<Map<Object, Object>> toTreeData(List<?> list) {
        if (list == null || list.size() == 0)
            return null;
        /* 判断传入集合泛型是Map还是Vo */
        boolean isMap = false;
        if (list.get(0) instanceof Map) {
            isMap = true;
        }

        String keyFieldName = "id";
        String codeFieldName = "code";
        String titleFieldName = "name";
        String parentKeyName = "parentId";
        String levelNumFieldName = "levelNum";
        String leafFieldName = "leaf";

        Map<Object, Map<Object, Object>> map = new HashMap<Object, Map<Object, Object>>();// 根据ID：BEAN的模式将树节点存入Map，方便取用
        List<Map<Object, Object>> rootList = new ArrayList<Map<Object, Object>>();// 根节点集合
        int startLevelNum = 0;// 获取级次最低的层级，因为有的数据不是从根节点开始查询
        int index = 0;// 遍历集合索引
        for (Object obj : list) {
            HashMap<Object, Object> tempMap = new HashMap<Object, Object>();// 树节点对象
            // 如果是Vo，将Vo转化为Map
            if (isMap) {
                tempMap.putAll((Map<Object, Object>)obj);
            } else {

                TreeConfig treeConfig = obj.getClass().getAnnotation(TreeConfig.class);
                if (treeConfig != null) {
                    keyFieldName = treeConfig.key();
                    codeFieldName = treeConfig.code();
                    titleFieldName = treeConfig.title();
                    parentKeyName = treeConfig.parentKey();
                    levelNumFieldName = treeConfig.levelNum();
                    leafFieldName = treeConfig.leaf();
                }

                Map<Object, Object> beanMap = new BeanMap(obj);
                tempMap.putAll(beanMap);
            }
            Object id = tempMap.get(keyFieldName);
            Object code = tempMap.get(codeFieldName);
            Object name = tempMap.get(titleFieldName);
            Object pId = tempMap.get(parentKeyName);
            Object levelNumObj = tempMap.get(levelNumFieldName);
            Object isLeaf = tempMap.get(leafFieldName);
            if (null != isLeaf) {
                if ("0".equals(isLeaf)) {
                    tempMap.put("hasChildren", true);
                }
                if ("1".equals(isLeaf)) {
                    tempMap.put("hasChildren", false);
                }
            }
            int levelNum = levelNumObj == null ? 0 : Integer.parseInt(levelNumObj.toString());
            if (index == 0) {
                startLevelNum = levelNum;
            } else {
                if (startLevelNum > levelNum) {
                    throw new BaseException("数据未按级次排序");
                }
            }

            // 处理必有属性
            tempMap.putIfAbsent("id", id);
            tempMap.putIfAbsent("code", code);
            tempMap.putIfAbsent("name", name);
            tempMap.putIfAbsent("parentId", pId);
            tempMap.putIfAbsent("levelNum", levelNum);
            tempMap.putIfAbsent("leaf", isLeaf);
            tempMap.putIfAbsent("children", new ArrayList<Map<Object, Object>>());// 预制节点的下级节点集合

            map.put(id, tempMap);
            // 没有父ID、级次为1或者级次为最低级次的数据存入根节点集合中，否则存入对应父节点的子集合中
            if (pId == null || 1 == levelNum || startLevelNum == levelNum) {
                rootList.add(tempMap);
            } else {
                if (map.get(pId) == null) {
                    throw new BaseException("数据未按级次排序或者" + tempMap.get("title").toString() + "没有上级节点");
                }
                List<Map<Object, Object>> children = (List<Map<Object, Object>>)map.get(pId).get("children");
                children.add(tempMap);
            }
            index++;
        }
        return rootList;
    };

    /**
     * 适用场景：树结构模糊搜索，进行模糊搜索过滤再封装成ant-tree要求的结构 要求：为了提高效率，本方法默认参数集合的数据是按照层级升序，所以调用时请处理好参数集合的排序
     * 优点：采用倒序遍历，仍然只需要遍历一次即可，效率高；且可以接受任意泛型的集合参数，通用性好
     *
     * @param List
     *            数据集合
     * @param likeName
     *            模糊查询关键字
     * @return List<Map<Object, Object>> ant-tree树结构数据根节点集合
     * @author zhoujiej
     */
    @SuppressWarnings("unchecked")
    public static List<Map<Object, Object>> toTreeDataWithSearch(List<?> list, String likeName) {
        if (list == null || list.size() == 0)
            return null;
        boolean isMap = false;
        String keyFieldName = "id";
        String codeFieldName = "code";
        String titleFieldName = "name";
        String parentKeyName = "parentId";
        String levelNumFieldName = "levelNum";
        String leafFieldName = "leaf";

        int startlevelNum = 1;// 获取级次最低的层级
        if (list.get(0) instanceof Map) {
            isMap = true;
            startlevelNum = Integer.parseInt(((Map<Object, Object>)list.get(0)).get("levelNum").toString());
        } else {
            TreeConfig treeConfig = list.get(0).getClass().getAnnotation(TreeConfig.class);
            if (treeConfig != null) {
                keyFieldName = treeConfig.key();
                codeFieldName = treeConfig.code();
                titleFieldName = treeConfig.title();
                parentKeyName = treeConfig.parentKey();
                levelNumFieldName = treeConfig.levelNum();
                leafFieldName = treeConfig.leaf();
            }
            startlevelNum = Integer.parseInt(new BeanMap(list.get(0)).get("levelNum").toString());
        }

        Map<Object, Map<Object, Object>> map = new HashMap<>();
        List<Map<Object, Object>> rootList = new LinkedList<>();

        int size = list.size();
        for (int i = size - 1; i >= 0; i--) {
            Object obj = list.get(i);
            HashMap<Object, Object> tempMap = new HashMap<Object, Object>();
            // 如果是Vo，将Vo转化为Map
            if (isMap) {
                tempMap.putAll((Map<Object, Object>)obj);
            } else {
                Map<Object, Object> beanMap = new BeanMap(obj);
                tempMap.putAll(beanMap);
            }
            Object id = tempMap.get(keyFieldName);
            Object code = tempMap.get(codeFieldName);
            Object name = tempMap.get(titleFieldName);
            Object pId = tempMap.get(parentKeyName);
            Object levelNumObj = tempMap.get(levelNumFieldName);
            Object isLeaf = tempMap.get(leafFieldName);
            int levelNum = levelNumObj == null ? 0 : Integer.parseInt(levelNumObj.toString());
            if (null != isLeaf) {
                if ("0".equals(isLeaf)) {
                    tempMap.put("hasChildren", true);
                }
                if ("1".equals(isLeaf)) {
                    tempMap.put("hasChildren", false);
                }
            }

            // 处理必有属性
            tempMap.putIfAbsent("id", id);
            tempMap.putIfAbsent("code", code);
            tempMap.putIfAbsent("name", name);
            tempMap.putIfAbsent("parentId", pId);
            tempMap.putIfAbsent("levelNum", levelNum);
            tempMap.putIfAbsent("leaf", isLeaf);

            // 节点已存在于existMap则将该节点合并；不存在则判断是不是符合查询条件，是则保存该节点
            Map<Object, Object> existMap = map.get(id);
            if (existMap != null && existMap.get("children") != null) {
                Object children = existMap.get("children");
                existMap.putAll(tempMap);
                // 解决如果参数对象本身有children属性的问题
                existMap.put("children", children);
            } else if ((name != null && name.toString().contains(likeName))
                || (tempMap.get("spell") != null && tempMap.get("spell").toString().contains(likeName))
                || (tempMap.get("spellInitial") != null && tempMap.get("spellInitial").toString().contains(likeName))) {
                map.put(id, tempMap);
            } else {
                continue;
            }

            tempMap = (HashMap<Object, Object>)map.get(id);
            // 没有父ID、级次为1或者级次为最低级次的数据存入根节点集合中，否则存入对应父节点的子集合中
            if (pId == null || 1 == levelNum || startlevelNum == levelNum) {
                rootList.add(0, tempMap);
            } else {
                // 如果能找到父节点，则添加到父节点的children集合中，找不到则创建父节点
                Map<Object, Object> parentMap = map.get(pId);
                List<Map<Object, Object>> children = new LinkedList<Map<Object, Object>>();// 因为要从0索引插入数据，所以采用LinkedList效率较高
                if (parentMap == null) {
                    parentMap = new HashMap<Object, Object>();
                    parentMap.put("children", children);
                    map.put(pId, parentMap);
                } else {
                    children = (List<Map<Object, Object>>)parentMap.get("children");
                }
                // 8月22日zhaoxtg加，添加为空判断
                if (children == null) {
                    children = new LinkedList<Map<Object, Object>>();
                }
                children.add(0, tempMap);
            }
        }
        return rootList;
    };

    /**
     * 适用新老部门树
     *
     * @param list
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<Map<Object, Object>> toTreeDataNew(List<?> list) {
        if (list == null || list.size() == 0)
            return null;
        /* 判断传入集合泛型是Map还是Vo */
        boolean isMap = false;
        if (list.get(0) instanceof Map)
            isMap = true;

        Map<Object, Map<Object, Object>> map = new HashMap<Object, Map<Object, Object>>();// 根据ID：BEAN的模式将树节点存入Map，方便取用
        List<Map<Object, Object>> rootList = new ArrayList<Map<Object, Object>>();// 根节点集合
        for (Object obj : list) {
            HashMap<Object, Object> tempMap = new HashMap<Object, Object>();// 树节点对象
            // 如果是Vo，将Vo转化为Map
            if (isMap) {
                tempMap.putAll((Map<Object, Object>)obj);
            } else {
                Map<Object, Object> beanMap = new BeanMap(obj);
                tempMap.putAll(beanMap);
            }
            Object id = tempMap.get("id");
            Object pId = tempMap.get("parentId");
            Object levelNumObj = tempMap.get("levelNum");
            int levelNum = levelNumObj == null ? 0 : Integer.parseInt(levelNumObj.toString());
            // 因为前台树组件使用key作为node的唯一标识，所以将没有key的数据存上id
            if (tempMap.get("key") == null)
                tempMap.put("key", id);
            if (tempMap.get("title") == null)
                tempMap.put("title", tempMap.get("name"));
            tempMap.put("children", new ArrayList<Map<Object, Object>>());// 预制节点的下级节点集合
            map.put(id, tempMap);
            // 没有父ID或级次为1的数据存入根节点集合中，否则存入对应父节点的子集合中
            if (pId == null || 1 == levelNum) {
                rootList.add(tempMap);
            } else {
                if (map.get(pId) == null) {
                    throw new BaseException("数据未按级次排序或者" + tempMap.get("title").toString() + "没有上级节点");
                }
                List<Map<Object, Object>> children = (List<Map<Object, Object>>)map.get(pId).get("children");
                children.add(tempMap);
            }
        }
        return rootList;
    }

    /**
     * 适用新老部门树
     *
     * @param list
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<Map<Object, Object>> toTreeDataWithSearchNew(List<?> list, String likeName) {
        if (list == null || list.size() == 0)
            return null;
        boolean isMap = false;
        if (list.get(0) instanceof Map) {
            isMap = true;
        }

        Map<Object, Map<Object, Object>> map = new HashMap<Object, Map<Object, Object>>();
        List<Map<Object, Object>> rootList = new LinkedList<Map<Object, Object>>();

        int size = list.size();
        for (int i = size - 1; i >= 0; i--) {
            Object obj = list.get(i);
            HashMap<Object, Object> tempMap = new HashMap<Object, Object>();
            // 如果是Vo，将Vo转化为Map
            if (isMap) {
                tempMap.putAll((Map<Object, Object>)obj);
            } else {
                Map<Object, Object> beanMap = new BeanMap(obj);
                tempMap.putAll(beanMap);
            }
            Object id = tempMap.get("id");
            Object pId = tempMap.get("parentId");
            Object title = tempMap.get("title");
            Object levelNumObj = tempMap.get("levelNum");
            int levelNum = levelNumObj == null ? 0 : Integer.parseInt(levelNumObj.toString());
            if (tempMap.get("key") == null)
                tempMap.put("key", id);

            if (title == null) {
                title = tempMap.get("name");
                tempMap.put("title", title);
            }

            // 节点已存在于existMap则将该节点合并；不存在则判断是不是符合查询条件，是则保存该节点
            Map<Object, Object> existMap = map.get(id);
            if (existMap != null && existMap.get("children") != null) {
                existMap.putAll(tempMap);
            } else if (title != null && title.toString().contains(likeName)) {
                map.put(id, tempMap);
            } else {
                continue;
            }

            tempMap = (HashMap<Object, Object>)map.get(id);
            // 没有父ID或级次为1的数据存入根节点集合中，否则存入对应父节点的子集合中
            if (pId == null || 1 == levelNum) {
                rootList.add(0, tempMap);
            } else {
                // 如果能找到父节点，则添加到父节点的children集合中，找不到则创建父节点
                Map<Object, Object> parentMap = map.get(pId);
                List<Map<Object, Object>> children = new LinkedList<Map<Object, Object>>();// 因为要从0索引插入数据，所以采用LinkedList效率较高
                if (parentMap == null) {
                    parentMap = new HashMap<Object, Object>();
                    parentMap.put("children", children);
                    map.put(pId, parentMap);
                } else {
                    children = (List<Map<Object, Object>>)parentMap.get("children");
                }
                // 8月22日zhaoxtg加，添加为空判断
                if (children == null) {
                    children = new LinkedList<Map<Object, Object>>();
                }
                children.add(0, tempMap);
            }
        }
        return rootList;
    }

    /**
     * 封装树结构
     *
     * @param list
     *            按层级排序的集合
     * @return List<TreeVO>
     */
    public static void toTreeDataWithSum(List<? extends TreeVO> list) {
        toTreeDataWithSum(list, false);
    }

    /**
     *
     * 封装树结构
     *
     * @param list
     *            按层级排序的集合
     * @param isTitleLayered
     *            是否在显示名称前加空格前缀用以分层
     * @return List<TreeVO>
     */
    public static void toTreeDataWithSum(List<? extends TreeVO> list, boolean isTitleLayered) {
        if (list == null || list.isEmpty()) {
            return;
        }

        TreeVO firstTreeNodeVO = list.get(0);
        Class clazz = firstTreeNodeVO.getClass();
        int startlevelNum = firstTreeNodeVO.getLevelNum();
        // List<TreeVO> rootList = new LinkedList<TreeVO>();// 根节点集合
        Map<String, List<TreeVO>> parentMap = new HashMap<String, List<TreeVO>>();// 子集Map
        Map<String, BigDecimal[]> sumMap = new HashMap<String, BigDecimal[]>();// 求和列Map

        TreeConfig treeConfig = list.get(0).getClass().getAnnotation(TreeConfig.class);
        if (treeConfig == null) {
            throw new BaseException("参数缺少@TreeConfig注解");
        }
        String keyFieldName = treeConfig.key();
        String titleFieldName = treeConfig.title();
        String parentKeyName = treeConfig.parentKey();
        String levelNumFieldName = treeConfig.levelNum();
        String leafFieldName = treeConfig.leaf();
        String[] fieldName = treeConfig.sumProperties();

        boolean needSum = fieldName.length > 0;
        int size = list.size();
        for (int i = size - 1; i >= 0; i--) {
            TreeVO treeNodeVO = list.get(i);
            Object keyObj = ReflectionUtils.getValue(clazz, keyFieldName, treeNodeVO);
            Object titleObj = ReflectionUtils.getValue(clazz, titleFieldName, treeNodeVO);
            Object parentKeyObj = ReflectionUtils.getValue(clazz, parentKeyName, treeNodeVO);
            Object levelNumObj = ReflectionUtils.getValue(clazz, levelNumFieldName, treeNodeVO);
            Object leafObj = ReflectionUtils.getValue(clazz, leafFieldName, treeNodeVO);
            if (keyObj == null || titleObj == null) {
                throw new BaseException("树结构数据唯一标识和显示名称不能为空");
            }
            String key = keyObj.toString();
            String title = titleObj.toString();
            String parentKey = parentKeyObj == null ? null : parentKeyObj.toString();
            int levelNum = (Integer)levelNumObj;
            String leaf = leafObj.toString();
            treeNodeVO.setKey(key);
            treeNodeVO.setParentKey(parentKey);
            treeNodeVO.setLevelNum(levelNum);
            treeNodeVO.setLeaf(leaf);
            treeNodeVO.setTitle(isTitleLayered ? getPrefix(PREFIX_SPACE, levelNum - startlevelNum) + title : title);

            if (WhetherConsts.NO.equals(leaf)) {
                List<TreeVO> currentchildren = parentMap.get(key);
                if (currentchildren != null) {
                    // if (title.contains(likeName)) {
                    treeNodeVO.setChildren(currentchildren);
                    treeNodeVO.setRowEditable(WhetherConsts.NO);
                    if (needSum) {
                        setSum(treeNodeVO, fieldName, sumMap.get(key));
                    }
                    // }
                } else {
                    treeNodeVO.setChildren(new LinkedList<TreeVO>());
                }
            }

            // 没有父ID、级次为1或者级次为最低级次的数据存入根节点集合中，否则存入对应父节点的子集合中
            if (parentKey == null || 1 == levelNum || startlevelNum == levelNum) {
                // rootList.add(0, treeNodeVO);
                continue;
            } else {
                List<TreeVO> parentChildren = parentMap.get(parentKey);
                BigDecimal[] sumArr = sumMap.get(parentKey);
                if (parentChildren == null) {
                    parentChildren = new LinkedList<TreeVO>();
                    parentMap.put(parentKey, parentChildren);
                    sumArr = new BigDecimal[fieldName.length];
                    sumMap.put(parentKey, sumArr);
                }
                parentChildren.add(0, treeNodeVO);
                list.remove(i);
                if (needSum) {
                    sum(sumArr, fieldName, treeNodeVO);
                }
            }
        }
        // return rootList;
    }


    /**
     * <p> 暂时只适用于公安部转移支付省级下达编辑页面导出 </p>
     * @param mapList
     * @return void
     * @author wudingjia
     * @date 2021-01-06 11:03
     */
    public static void toTreeMap(List<Map<String,Object>> mapList){

        //记录元素的下标
        Map<String, Integer> idIndex = new HashMap<>();

        List<Map<String,Object>> tempMapList = new ArrayList<>(mapList.size());

        for (int i = 0; i < mapList.size(); i++) {
            Map<String, Object> map = mapList.get(i);
            String releaseOrgId = (String)map.get("RELEASE_ORG_ID");
            String parentId = (String)map.get("PARENT_ID");
            int levelNum = (int)map.get("LEVEL_NUM");
            String isLeaf = (String)map.get("IS_LEAF");
            StringBuilder space = new StringBuilder();
            String releaseOrgName =(String) map.get("RELEASE_ORG_NAME");
            for (int j = 0; j < levelNum; j++) {
                space.append(PREFIX_SPACE);
            }
            //名称显示层级
            map.put("RELEASE_ORG_NAME",space.toString()+releaseOrgName);
            if(WhetherConsts.NO.equals(isLeaf)){
                idIndex.put(releaseOrgId,i);
            }
            if(4 == (levelNum)){
                Integer index = idIndex.get(parentId);
                tempMapList.add(index+1,map);
                //后面map指针向后移动1
                for (Map.Entry<String, Integer> stringIntegerEntry : idIndex.entrySet()) {
                    if (stringIntegerEntry.getValue()>index){
                        idIndex.put(stringIntegerEntry.getKey(), stringIntegerEntry.getValue()+1);
                    }
                }
                //将父id存为当前i
                idIndex.put(parentId,index+1);
                continue;
            }
            tempMapList.add(i,map);

        }
        mapList.clear();
        mapList.addAll(tempMapList);

    }

    private static String getPrefix(String prefixSpace, int d) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < d; i++) {
            stringBuffer.append(PREFIX_SPACE);
        }
        return stringBuffer.toString();
    }

    private static void setSum(TreeVO treeNodeVO, String[] fieldNames, BigDecimal[] sumArr) {
        for (int i = 0; i < sumArr.length; i++) {
            BigDecimal sum = sumArr[i];
            String fieldName = fieldNames[i];
            ReflectionUtils.setValue(treeNodeVO.getClass(), fieldName, treeNodeVO, sum);
        }

    }

    private static void sum(BigDecimal[] sumArr, String[] fieldName, TreeVO treeNodeVO) {
        for (int i = 0; i < sumArr.length; i++) {
            BigDecimal sum = sumArr[i];
            Object valueObj = ReflectionUtils.getValue(treeNodeVO.getClass(), fieldName[i], treeNodeVO);
            BigDecimal value = valueObj == null ? new BigDecimal(0) : (BigDecimal)valueObj;
            if (sum == null) {
                sumArr[i] = value;
            } else {
                sumArr[i] = sum.add(value);
            }
        }

    };
}
