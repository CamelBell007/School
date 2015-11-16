package camlebell.com.request;

/**
 * @author sunny
 *
 */
public interface Request {
	/**
	 * ������������.
	 * 
	 * @param type
	 *            ��������.
	 */
	void setService(String service);
	
	/**
	 * ������������.
	 * 
	 * @param content
	 *            ��������.
	 * @throws Exception
	 *             �쳣.
	 */
	void setContent(Object content) throws Exception;
	
	/**
	 * ���÷���������.
	 * 
	 * @param reponse
	 *            ����������.
	 * @throws Exception
	 *             �쳣.
	 */
	void setResponse(String response) throws Exception;
	
	
	/**
	 * ���÷�����������δ��ת���ģ�
	 * @param reponse
	 * @throws Exception
	 */
	void setResponseString(String response) throws Exception;

	/**
	 * ��ȡ����״̬.
	 * 
	 * @return ����״̬.
	 * @throws Exception
	 *             �쳣.
	 */
	int getStatus();
	
	
	/**
	 * ��ȡ��������.
	 * 
	 * @return ��������.
	 * @throws Exception
	 *             �쳣.
	 */
	Object getData();
	
	
	/**
	 * ��ȡ������
	 */
	int getCode();
	
	
	/**
	 * ��ȡ������Ϣ
	 */
	String getMessage();
	
	
	/**
	 * �����Ƿ�ɹ�.
	 * 
	 * @return �Ƿ�ɹ�.
	 */
	boolean success();
	
	public static enum RequestStatus {
		SUCCESS(0),FAILED(1) ;

		private int mValue;

		private RequestStatus(int value) {
			mValue = value;
		}

		public boolean is(int value) {
			return value == mValue;
		}

		public int value() {
			return mValue;
		}
	}
	

}
