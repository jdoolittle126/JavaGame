uniform sampler2D TextureUnit0;
uniform sampler2D TextureUnit1 ;

void main(void)
{
	vec4 value1 = texture2D(TextureUnit0, vec2(gl_TexCoord[0]));
	vec4 value2 = texture2D(TextureUnit1, vec2(gl_TexCoord[1]));
	
	gl_FragColor = (value1+value2) * 0.5;
}
