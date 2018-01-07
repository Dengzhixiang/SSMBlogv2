package service;

import javax.security.sasl.SaslException;

import mapping.UsersMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utils.MD5;
import entity.Users;
import entity.UsersExample;

@Service
public class LoginService {
	@Autowired
	private UsersMapper usersMapper;

	public boolean validate(String username, String password) throws SaslException {
		Users user;
		UsersExample example  = new UsersExample();
		example.createCriteria().andUsernameEqualTo(username);
		
		if(usersMapper.selectByExample(example) != null) {
			user = usersMapper.selectByExample(example).get(0);
		} else {
			// �û������ڣ���½ʧ�ܣ�
			return false;
		}
	
		// ��ȡ���ݿ������
		String passwordDB = user.getPassword();
		// password md5 ����
		String passmd5 = MD5.getMD5(password);
		System.out.println(passmd5);
		if(passwordDB.equals(passmd5))
			return true;
		// �������
		return false;
	}
	
}
