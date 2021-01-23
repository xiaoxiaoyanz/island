package com.wucc.island.common.util;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;

import com.google.common.collect.Lists;

/**
 * 
 * @author 作者 yinyla@yonyou.com:
 * 
 * @version 创建时间：May 27, 2019 5:24:38 PM
 * 
 *          加载指定路径class文件
 */
public class ClassScanner {

	private static List<TypeFilter> includes = Lists.newArrayList();

	private ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();;

	private MetadataReaderFactory metadataFactory = new CachingMetadataReaderFactory(
			this.patternResolver);

	public static List<Class<?>> scan(String path,
			Class<? extends Annotation>... annotations) {
		ClassScanner scanner = new ClassScanner();
		for (Class<? extends Annotation> clazz : annotations) {
			includes.add(new AnnotationTypeFilter(clazz));
		}

		return scanner.doScan(path);
	}

	public List<Class<?>> doScan(String path) {
		List<Class<?>> ret = Lists.newArrayList();
		String scanPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
				+ ClassUtils.convertClassNameToResourcePath(path) + "/*.class";
		try {
			Resource[] resources = patternResolver.getResources(scanPath);
			for (int i = 0, length = resources.length; i < length; i++) {
				Resource resource = resources[i];
				MetadataReader metadata = metadataFactory
						.getMetadataReader(resource);
				if (includes.size() != 0 && metadata != null && match(metadata)) {
					ret.add(Class.forName(metadata.getClassMetadata()
							.getClassName()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	private boolean match(MetadataReader metadata) throws IOException {
		for (TypeFilter filter : includes) {
			if (filter.match(metadata, metadataFactory)) {
				return true;
			}
		}
		return false;
	}
}
