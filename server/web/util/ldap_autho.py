import ldap,sys

def check_credentials(username, password):
   """Verifies credentials for username and password.
   Returns None on success or a string describing the error on failure
   # Adapt to your needs
   """
   LDAP_SERVER = 'ldap://172.24.1.102:389'
   # fully qualified AD user name
   LDAP_USERNAME = '%s' % username
   # your password
   LDAP_PASSWORD = password
   base_dn = 'DC=LGE,DC=NET'
   ldap_filter = 'userPrincipalName=%s' % username
   attrs = ['memberOf']
   try:
       # build a client
       ldap_client = ldap.initialize(LDAP_SERVER)
       #print ldap_client
       # perform a synchronous bind
       ldap_client.set_option(ldap.OPT_REFERRALS,0)
       ldap_client.simple_bind_s(LDAP_USERNAME, LDAP_PASSWORD)
   except ldap.INVALID_CREDENTIALS:
       ldap_client.unbind()
       return 'Wrong username ili password'
   except ldap.SERVER_DOWN:
       return 'AD server not awailable'
   # all is well
   ldap_client.unbind()
   return None

def main(argv):
    if(len(argv)>=2):
        username = argv[0]
        password = argv[1]
        print (check_credentials(username,password))
    else:
        print "fewer arguments are given"

if __name__ == '__main__':
    main(sys.argv[1:])
