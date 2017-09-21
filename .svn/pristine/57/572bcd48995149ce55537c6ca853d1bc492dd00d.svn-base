package com.chen.config;

import javax.sql.DataSource;

import com.chen.tools.DebugInfo;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.c3p0.C3p0Plugin;

/**
 * 在数据库表有任何变动时，运行一下 main 方法，极速响应变化进行代码重构
 */
public class _JFinalDemoGenerator {

	public static DataSource getDataSource() {
		PropKit.use("db.txt");
		C3p0Plugin c3p0Plugin = MainConfig.createC3p0Plugin();
		c3p0Plugin.start();
		return c3p0Plugin.getDataSource();
	}

	public static void main(String[] args) {
		// base model 所使用的包名
		String baseModelPackageName = "com.chen.base.model";
		// base model 文件保存路径
		String baseModelOutputDir = PathKit.getWebRootPath()
				+ "/../src/com/chen/base/model";

		// model 所使用的包名 (MappingKit 默认使用的包名)
		String modelPackageName = "com.chen.domain";
		// model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
		String modelOutputDir = PathKit.getWebRootPath()
				+ "/../src/com/chen/domain/";

		// 创建生成器
		Generator gernerator = new Generator(getDataSource(),
				baseModelPackageName, baseModelOutputDir, modelPackageName,
				modelOutputDir);
		System.out.println("创建生成器完成");
		// 添加不需要生成的表名
		// gernerator.addExcludedTable("");
		// 设置是否在 Model 中生成 dao 对象
		gernerator.setGenerateDaoInModel(true);
		// 设置是否生成字典文件
		gernerator.setGenerateDataDictionary(true);
		DebugInfo.println("生成字典文件完成");
		// 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为
		// "User"而非 OscUser
		gernerator.setRemovedTableNamePrefixes("chen_");
		// 生成
		gernerator.generate();
		// 打印完成
		DebugInfo.println("数据库创建完成...");
	}

}
