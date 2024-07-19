class Team:
   '''
   Contains List of Junior and senior team members
   '''
   def __init__(self):
       self._juniorMembers = list()
       self._seniorMembers = list()
   def addJuniorMembers(self, members):
       self._juniorMembers += members
   def addSeniorMembers(self, members):
       self._seniorMembers += members
