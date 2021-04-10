package kr.or.ddit.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class BuildSqlMapClient {
   private static SqlMapClient smc;
   
   static {
		Reader rd = null;
		try {
			
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);

		
			rd = Resources.getResourceAsReader("sqlMapConfig.xml");

			
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);

		
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 자원 반납

			if (rd != null) try {rd.close();} catch (IOException e2) {   }
		}
   }
		public static SqlMapClient getSqlMapClient() {
			return smc;
		}
	}
   

